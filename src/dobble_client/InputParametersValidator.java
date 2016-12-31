
package dobble_client;


public class InputParametersValidator {
    public int validatedPort;
    public String validatedIP;
    
    public void InputParametersValidator() {
        validatedPort = -1;
        validatedIP = null;
    }
    
    public String checkParameters(String ip, String port) {
        if (checkPort(port) < 0) {
            return "Port needs to be in range <1025;65000>";
        }
        if (checkIP(ip) < 0) {
            return "IP address must be in format: \nX.X.X.X\n X is number in range <0;255>";
        }
        return null;
    }
    
    private int checkPort(String portStr) {
        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (NumberFormatException e) {
            return -6;
        }
        if (port < 1024 || port > 65000) {
            return -1;
        } 
        validatedPort = port;
        return 0;
    }
    
    private int checkIP(String ip) {
        /*String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return -2;
        }
        int num;
        for (String part:parts) {
            try {
            num = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                return -3;
            }
            if (num < 0 || num > 255) {
                return -4;
            }
        }*/
        validatedIP = ip;
        return 0;
    }
    
}
