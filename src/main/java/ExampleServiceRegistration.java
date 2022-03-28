import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

public class ExampleServiceRegistration {

    public static void main(String[] args) throws InterruptedException {

        try {
            // Create a JmDNS instance
            JmDNS jmdnsReg = JmDNS.create(InetAddress.getLocalHost());
            InetAddress jmsAddr = jmdnsReg.getInterface();

            System.out.println("Registration jmdns instance created: " + jmdnsReg.getName());
            System.out.println("Registration jmdns instance ip address: " + jmsAddr);

            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create("_userLogin._tcp.local", "user_login", 50555, "path=index.html");
            jmdnsReg.registerService(serviceInfo);
            Thread.sleep(25000);
            ServiceInfo info = jmdnsReg.getServiceInfo("_userLogin._tcp.local.", "user_login", 5000);
            System.out.println("Details of the service that was registered " + info);

            // Wait a bit
            Thread.sleep(25000);

            // Unregister all services
            //jmdns.unregisterAllServices();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
