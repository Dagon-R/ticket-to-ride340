package Services;

public interface Service {
    void connectToProxy(Object... obj);
    Object doService(Object... obj);
}
