package ViewGraphical;

/**
 * Created by ahan on 6/3/17.
 */

import Model.*;
import javax.swing.*;

public class GraphicalInvoice {
    private InstallationManager myInstallation;
    private Customer myCustomer;

    /**
     * The constructor for creating the window where the invoice is displayed
     * @param myInstallation installation manager for the program
     * @param myCustomer the customer object containing the customer data
     */
    public GraphicalInvoice(InstallationManager myInstallation, Customer myCustomer){
        this.myInstallation=myInstallation;
        this.myCustomer=myCustomer;

        JFrame invoiceFrame = new JFrame("Invoice");
        JPanel level1Panel = new JPanel();
        invoiceFrame.add(level1Panel);
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));

        level1Panel.add(new JLabel("Contract ID: " + myCustomer.getId()));
        level1Panel.add(new JLabel("Name: " + myCustomer.getName()));
        level1Panel.add(new JLabel("Address: " + myCustomer.getAddress()));
        level1Panel.add(new JLabel("Emergency Number 1: " +myCustomer.getEmergencyNumber1()));
        level1Panel.add(new JLabel("Emergency Number 2: " +myCustomer.getEmergencyNumber2()));
        level1Panel.add(new JLabel("Customer ID: " + myCustomer.getCustomerID()));
        level1Panel.add(new JLabel("Effective Date: " + myCustomer.getEffectiveDate()));

        level1Panel.add((new JLabel(" ")));

        if (myInstallation.getSecuritySensorSystem().isSystemInstalledBefore()){
            level1Panel.add((new JLabel("Security System Installation Fee: $200")));
        }else{
            level1Panel.add((new JLabel("No Security System Installation Fee: $0")));
        }
        level1Panel.add(new JLabel("Sensor Installation: $50 x "+myInstallation.getSecuritySensorSystem().getSensorsInstalledForCurrentMonth()));
        level1Panel.add(new JLabel("Call Center Service Charge: $20 x "+myInstallation.getSecuritySensorSystem().getTimesTriggeredWithCall()));
        int securitySystemTotalCost = (myInstallation.getSecuritySensorSystem().isSystemInstalledBefore() ? 1 : 0) * 200 + 50*myInstallation.getSecuritySensorSystem().getSensorsInstalledForCurrentMonth() +20*myInstallation.getSecuritySensorSystem().getTimesTriggeredWithCall();
        level1Panel.add(new JLabel("Security System Total Cost: $"+securitySystemTotalCost));

        level1Panel.add((new JLabel(" ")));

        if (myInstallation.getFireAlarmSystem().isSystemInstalledBefore()){
            level1Panel.add((new JLabel("Fire Alarm System Installation Fee: $300")));
            if (myInstallation.getSecuritySensorSystem().isSystemInstalledBefore())
                level1Panel.add((new JLabel("Security System Installation Discount: -$60")));
        }else{
            level1Panel.add((new JLabel("No Fire Alarm System Installation Fee: $0")));
        }

        level1Panel.add(new JLabel("Sensor Installation: $100 x "+myInstallation.getFireAlarmSystem().getSensorsInstalledForCurrentMonth()));
        level1Panel.add(new JLabel("Call Center Service Charge: $50 x "+myInstallation.getFireAlarmSystem().getTimesTriggeredWithCall()));
        int fireAlarmSystemTotalCost = (myInstallation.getFireAlarmSystem().isSystemInstalledBefore() ? 1 : 0) * (300 + (myInstallation.getSecuritySensorSystem().isSystemInstalledBefore() ? 1 : 0)*-60)+ 100*myInstallation.getFireAlarmSystem().getSensorsInstalledForCurrentMonth() +50*myInstallation.getFireAlarmSystem().getTimesTriggeredWithCall();
        level1Panel.add(new JLabel("Fire Alarm System Total Cost: $"+fireAlarmSystemTotalCost));

        level1Panel.add((new JLabel(" ")));

        int totalCost = fireAlarmSystemTotalCost + securitySystemTotalCost;

        level1Panel.add((new JLabel("Total Cost: $" + totalCost)));

        //invoiceFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        invoiceFrame.pack();
        invoiceFrame.setVisible(true);
    }
    public static void main(String[] args){
        GraphicalInvoice myInvoice = new GraphicalInvoice(new InstallationManager(),
                new Customer("001", "Santa Clara University",
                "500 El Camino Real", "911",
                "408-277-8900", "registrar@scu.edu", "04/04/2017-04/04/2018" ));
    }
}
