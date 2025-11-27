package GUI;

public abstract class BaseController {
    protected void navigate(String screen) {
        MainApp.showScreen(screen);
    }
}