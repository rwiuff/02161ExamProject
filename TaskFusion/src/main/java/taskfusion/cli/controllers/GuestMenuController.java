package taskfusion.cli.controllers;

import taskfusion.cli.components.Menu;
import taskfusion.cli.components.Text;
import taskfusion.cli.views.LoginView;
import taskfusion.cli.views.RegisterEmployeeView;
import taskfusion.persistency.Seeder;

public class GuestMenuController implements ControllerInterface {

    private boolean seededDemoData = false;

    private String[] menuOptions = {
            "Login",
            "Opret medarbejder",
            "Seed demo data",
            "Luk"
    };

    public void showMenu() {

        while (true) {
            int selectedMenuItem = Menu.showMenu(menuOptions, "Gæst menu");

            switch (selectedMenuItem) {
                case 1:
                    new LoginView().show();
                    break;
                case 2:
                    new RegisterEmployeeView().show();
                    break;
                case 3:
                    seedDemoData();
                    break;
                case 4:
                    System.out.println("--- FARVEL ---");
                    return; // NOTICE THIS RETURN, not break
                
                default:
                    Text.showError("Uventet menupunkt");
                    return; // NOTICE THIS RETURN, not break
            }
        }
    }

    /**
	 * Seeding
	 */
	private void seedDemoData() {
		if (seededDemoData) {
			Text.showError("Du har allerede seeded demo data");
			return;
		}
		

		try {
			Seeder seeder = new Seeder();
			seeder.seedDemoData();
			seededDemoData = true;
            Text.showSuccess("Demo data oprettet");
		} catch (Exception e) {
			Text.showExceptionError(e);
			e.printStackTrace();
		}
		
	}

}
