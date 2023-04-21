package taskfusion.domain;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import taskfusion.helpers.DateHelper;

public class ReportPDFGenerator {
    private String title;
    private String reportDate;
    private String projectLeader;
    private String projectNumber;
    private String startWeek;
    private String endWeek;
    private String customer;
    private Map<String, Employee> employees = new HashMap<>();
    private List<ProjectActivity> activities = new ArrayList<>();

    public ReportPDFGenerator(Report report) {
        this.title = report.getTitle();
        this.reportDate = getDateAsString(report.getReportDate());
        this.projectLeader = report.getProjectLeader().getInitials();
        this.projectNumber = report.getProjectNumber();
        this.startWeek = report.getStartWeek() + "";
        this.endWeek = report.getEndWeeek() + "";
        this.customer = report.getCustomer();
        this.employees = report.getEmployees();
        this.activities = report.getActivities();
    }

    public void save() throws IOException, URISyntaxException {
        String savePath = "./reports/" + projectNumber;
        generatePDF(savePath);
    }

    public void save(String saveDir) throws IOException, URISyntaxException {
        String savePath = saveDir + projectNumber;
        generatePDF(savePath);
    }

    public void generatePDF(String savePath) throws IOException, URISyntaxException {
        Document document = new Document(PageSize.A4, // New A4 document
                40f, // Left margin
                40f, // Right margin
                40f, // Top margin
                40f); // Bottom margin
        // Fonts
        Font titleFont = new Font(Font.COURIER, 20f, Font.BOLD);
        Font headerFont = new Font(Font.COURIER, 15f, Font.BOLD);
        Font cellFont = new Font(Font.COURIER, 12f);
        Font cellHeaderFont = new Font(Font.COURIER, 12f, Font.BOLD);

        Path path = Paths.get(savePath);
        Files.createDirectories(path);

        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(savePath + "/" + projectNumber + " " + title + " " + reportDate + ".pdf"));
        // Stream to save document as pdf

        // Header and footer
        HeaderFooter footer = new HeaderFooter(new Phrase("Side ", new Font(Font.COURIER, 12f)), true);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.setFooter(footer);

        document.open(); // Edit document

        // Add metadata
        document.addAuthor(projectLeader);
        document.addCreator("TaskFusion");
        document.addCreationDate();
        document.addTitle(title);

        // Set up logo
        ClassLoader classLoader = getClass().getClassLoader();
        Image logo = Image.getInstance(classLoader.getResource("TaskFusionAlt.png"));
        logo.setAlignment(Element.ALIGN_CENTER);
        logo.scalePercent(25f);
        document.add(logo);
        // Set up title
        Paragraph titleParagraph = new Paragraph("Projekt: " + title, titleFont);
        titleParagraph.setAlignment(Element.ALIGN_CENTER);
        document.add(titleParagraph);
        document.add(new Paragraph(Chunk.NEWLINE));

        // Add infobox
        Table infoBox = new Table(2);
        infoBox.setPadding(1f);
        infoBox.setSpacing(1f);
        infoBox.setWidth(100f);
        String[][] infoStrings = { { "Løbenr.: " + projectNumber, reportDate },
                { "Startuge: " + startWeek, "Leder: " + projectLeader },
                { "Slutuge: " + endWeek, "Kunde: " + customer } };
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                Cell cell = new Cell(new Phrase(infoStrings[i][j], cellFont));
                infoBox.addCell(cell, i, j);
            }
        }
        for (int i = 0; i < 3; i++) {
            Cell tmp = (Cell) infoBox.getElement(i, 1);
            tmp.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        }
        removeBorders(infoBox);
        document.add(infoBox);
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new LineSeparator());
        // document.add(new Paragraph(Chunk.NEWLINE));

        // Add employeelist
        Paragraph employeeHeader = new Paragraph("Medarbejdere",
                headerFont);
        employeeHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(employeeHeader);
        Table employeeBox = new Table(3);
        employeeBox.setPadding(1f);
        employeeBox.setSpacing(1f);
        employeeBox.setWidth(100f);
        String[] employeeTableHeader = { "Efternavn", "Fornavn", "Initialer" };
        for (int i = 0; i < 3; i++) {
            Cell cell = new Cell(new Phrase(employeeTableHeader[i], cellHeaderFont));
            employeeBox.addCell(cell, 0, i);
        }
        Set<String> employeesKeySet = employees.keySet();
        int row = 1;
        for (String employee : employeesKeySet) {
            Cell lastName = new Cell(new Phrase(employees.get(employee).getLastName(), cellFont));
            Cell firstName = new Cell(new Phrase(employees.get(employee).getFirstName(), cellFont));
            Cell initials = new Cell(new Phrase(employees.get(employee).getInitials(), cellFont));
            employeeBox.addCell(lastName, row, 0);
            employeeBox.addCell(firstName, row, 1);
            employeeBox.addCell(initials, row, 2);
            row++;
        }
        removeBorders(employeeBox);
        document.add(employeeBox);
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new LineSeparator());
        // document.add(new Paragraph(Chunk.NEWLINE));

        // Add activity info
        Paragraph activityHeader = new Paragraph("Projektaktiviteter",
                headerFont);
        activityHeader.setAlignment(Element.ALIGN_CENTER);
        document.add(activityHeader);
        for (ProjectActivity activity : activities) {
            Table activityInfoBox = new Table(2);
            activityInfoBox.setPadding(1f);
            activityInfoBox.setSpacing(1f);
            activityInfoBox.setWidth(100f);
            Table worktimeInfoBox = new Table(3);
            worktimeInfoBox.setPadding(1f);
            worktimeInfoBox.setSpacing(1f);
            worktimeInfoBox.setWidth(100f);
            Cell activityTitleCell = new Cell(new Phrase("Aktivitet: " + activity.title, cellHeaderFont));
            Cell timeBudgetCell = new Cell(
                    new Phrase("Budgeteret tid: " + activity.getTimeBudget(), cellHeaderFont));
            Cell starWeekCell = new Cell(new Phrase("Startuge: " + activity.startWeek, cellHeaderFont));
            Cell endWeekCell = new Cell(new Phrase("Slutuge: " + activity.endWeek, cellHeaderFont));
            timeBudgetCell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            endWeekCell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            activityInfoBox.addCell(activityTitleCell, 0, 0);
            activityInfoBox.addCell(timeBudgetCell, 0, 1);
            activityInfoBox.addCell(starWeekCell, 1, 0);
            activityInfoBox.addCell(endWeekCell, 1, 1);
            removeBorders(activityInfoBox);
            document.add(activityInfoBox);
            row = 0;
            for (WorktimeRegistration worktimeRegistration : activity.getWorktimeRegistrations()) {
                Cell dateCell = new Cell(new Phrase(getDateAsString(worktimeRegistration.getDate()), cellFont));
                Cell employeeCell = new Cell(new Phrase(worktimeRegistration.getInitials(), cellFont));
                Cell worktimeCell = new Cell(new Phrase(worktimeRegistration.getTime() + "", cellFont));
                worktimeCell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                worktimeInfoBox.addCell(dateCell, row, 0);
                worktimeInfoBox.addCell(employeeCell, row, 1);
                worktimeInfoBox.addCell(worktimeCell, row, 2);
                row++;
            }
            removeBorders(worktimeInfoBox);
            document.add(worktimeInfoBox);
        }

        // Close streams, document saved
        document.close();
        writer.close();
    }

    public void removeBorders(Table table) {
        table.disableBorderSide(Rectangle.LEFT);
        table.disableBorderSide(Rectangle.RIGHT);
        table.disableBorderSide(Rectangle.TOP);
        table.disableBorderSide(Rectangle.BOTTOM);
        for (int i = 0; i < table.getDimension().height; i++) {
            for (int j = 0; j < table.getDimension().width; j++) {
                Cell tmp = (Cell) table.getElement(i, j);
                tmp.disableBorderSide(Rectangle.LEFT);
                tmp.disableBorderSide(Rectangle.RIGHT);
                tmp.disableBorderSide(Rectangle.TOP);
                tmp.disableBorderSide(Rectangle.BOTTOM);
            }
        }
    }

    public String getDateAsString(Calendar date) {
        return DateHelper.getDateAsString(date);
    }

}
