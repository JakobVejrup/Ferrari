package com.presentation.mvc.controllers.leftnavbar;

import com.model.entities.Employee;
import com.model.enums.Occupation;
import com.presentation.mvc.controllers.employees.EmployeesController;
import com.presentation.mvc.controllers.login.LoginController;
import com.presentation.mvc.models.leftnavbar.LeftNavBarModel;
import com.presentation.mvc.models.leftnavbar.NavButtonModel;
import com.presentation.mvc.views.leftnavbar.LeftNavBarView;
import com.presentation.mvc.views.leftnavbar.NavButtonView;
import com.presentation.tools.facade.Facade;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class LeftNavbarController {
    private LeftNavBarModel model;
    private LeftNavBarView view;

    public LeftNavbarController() {
        Employee employee = Facade.getInstance().getLoggedIn();
        List<NavButtonController> controllers = new ArrayList<>();
        if(employee.getOccupation() == Occupation.Salesman || employee.getOccupation() == Occupation.Manager) {
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Ansatte"), (event) -> navigate("Employees")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "CAR", "Biler"), (event) -> navigate("Vehicles")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "FOLDER_OPEN", "Åbne Aftaler"), (event) -> navigate("AgreementsOpen")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "FOLDER", "Lukkede Aftaler"), (event) -> navigate("AgreementsClosed")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Kunder"), (event) -> navigate("Customers")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "SIGN_OUT", "Log Af"), (event) -> navigate("Login")));
        }
        if(employee.getOccupation() == Occupation.Admin) {
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Ansatte"), (event) -> navigate("Employees")));
            controllers.add(new NavButtonController(new NavButtonModel("FontAwesome", "USERS", "Kunder"), (event) -> navigate("Customers")));
        }
        List<NavButtonView> views = new ArrayList<>();
        for (NavButtonController c : controllers)
            views.add(c.getView());
        view = new LeftNavBarView(views);
    }
    public void navigate(String nav) {
        switch (nav) {
            case "Login" -> Facade.getInstance().logOff();
            case "Employees" -> Facade.getInstance().setCenter(new EmployeesController().getView());
            default -> {
                return;
            }
        }
    }
    public LeftNavBarView getView() {
        return view;
    }
}
