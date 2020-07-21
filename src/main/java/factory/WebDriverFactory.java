package factory;

public class WebDriverFactory {


    public WebDriverName createDriver(DriverType type) {
        switch (type) {
            case CHROME:
                return new Chrome();
            case FIREFOX:
                return new Firefox();
            default:
                throw new IllegalArgumentException("Wrong driver type:" + type);
        }
    }
}
