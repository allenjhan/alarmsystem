package ViewGraphical; /**
 * Created by ahan on 5/30/17.
 */

import Model.CurrentSystem;
import Model.Customer;
import Model.InstallationManager;
import Model.SensorSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphicalRooms {
    protected JFrame roomsFrame;

    protected InstallationManager myInstallationManager;
    protected Customer myCustomer;
    protected CurrentSystem currentSystem;

    protected JPanel timePanel;
    protected JPanel labelPanel;
    protected JButton managePasswordButton;
    protected JButton installSensorsButton;
    protected JButton activateSensorsButton;
    protected JButton setMalfunctionButton;
    protected JButton armSensorsButton;
    protected JButton triggerSensorsButton;
    protected JButton resetSensorsButton;
    protected JButton advancedArmingMenuButton;
    protected JButton printInvoiceButton;
    protected JButton changeCustomerButton;
    protected JPanel room1Panel;
    protected JPanel room2Panel;
    protected JPanel room3Panel;
    protected JPanel hallwayWestPanel;
    protected JPanel hallwayCenterPanel;
    protected JPanel hallwayEastPanel;
    protected JPanel room4Panel;
    protected JPanel room5Panel;
    protected JPanel room6Panel;
    protected ArrayList<JButton> roomButtons;
    protected JButton switchSystemButton;
    protected JButton installSecurityButton;
    protected JButton installFireAlarmButton;
    protected JButton sprinklersButton;
    protected JComboBox simulateComboBox;
    protected JButton simulateButton;
    protected JButton doneButton;
    protected JLabel currentSystemLabel;
    protected JLabel currentStatusLabel;

    public static class Room1FloorPlan extends JPanel{
        /**
         * paints the display image for room 1
         * @param g Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(30,30, 30, 400);
            g2d.drawLine(198,30, 198, 400);
            g2d.drawLine(30, 30, 400, 30);
        }
    }

    public static class Room2FloorPlan extends JPanel{
        /**
         * paints the display object for room 2
         * @param g Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(-200,30, 400, 30);
            g2d.drawLine(199,30, 199, 400);
        }
    }

    public static class Room3FloorPlan extends JPanel{
        /**
         * paints the display object for room 3
         * @param g Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(170,30, 170, 400);
            g2d.drawLine(-200, 30, 170, 30);
        }
    }

    public static class HallwayWestFloorPlan extends JPanel{
        /**
         * paints the display object for west part of hallway
         * @param g Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(30,-200,30,83);
            //g2d.drawLine(198,0,198,60);
            g2d.drawLine(30,1,98,1);
            g2d.drawLine(132,1,400, 1);

            g2d.drawLine(30,400,30,117);
            //g2d.drawLine(198,200,198,140);
            g2d.drawLine(30,199,98, 199);
            g2d.drawLine(132,199,300, 199);
        }
    }

    public static class HallwayCenterFloorPlan extends JPanel{
        /**
         * paint the display image for center of hallway
         * @param g the Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            //g2d.drawLine(198,0,198,60);
            g2d.drawLine(-200,1,83,1);
            g2d.drawLine(117,1,400,1);

            //g2d.drawLine(198,200,198,140);
            g2d.drawLine(-200,199,83, 199);
            g2d.drawLine(117,199,400, 199);
        }
    }

    public static class HallwayEastFloorPlan extends JPanel{
        /**
         * paint the display image for the east side of hallway
         * @param g the Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(170,-200,170,83);
            g2d.drawLine(-200,1,68, 1);
            g2d.drawLine(102,1,170, 1);

            g2d.drawLine(170,400,170,117);
            g2d.drawLine(-200,199,68, 199);
            g2d.drawLine(102,199,170, 199);
        }
    }

    public static class Room4FloorPlan extends JPanel{
        /**
         * paints display image for room 4
         * @param g Graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(30,170, 30, -200);
            g2d.drawLine(199,170, 199, -200);
            g2d.drawLine(30, 170, 400, 170);
        }
    }

    public static class Room5FloorPlan extends JPanel{
        /**
         * paints display image for room 5
         * @param g graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(-200,170, 400, 170);
            g2d.drawLine(198,170, 198, -200);
        }
    }

    public static class Room6FloorPlan extends JPanel{
        /**
         * paints display image for room 6
         * @param g graphics object used for painting
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2d.setPaint(Color.black);
            g2d.setStroke(new BasicStroke(2.0f));
            g2d.drawLine(170,170, 170, -200);
            g2d.drawLine(-200, 170, 170, 170);
        }
    }


    /**
     * Constructor for the superclass used for the room display of the floorplan
     * @param currentSystem the current system type of the window
     * @param myInstallationManager the installation manager of the program
     * @param myCustomer holds the customer data
     */
    public GraphicalRooms(CurrentSystem currentSystem,
                          InstallationManager myInstallationManager,
                          Customer myCustomer){
        this.currentSystem = currentSystem;
        this.myInstallationManager = myInstallationManager;
        this.myCustomer = myCustomer;
        roomsFrame = new JFrame("SoSafe Security Systems");

        JPanel level1Panel = new JPanel(); //top level panel
        roomsFrame.add(level1Panel);
        level1Panel.setLayout(new BorderLayout());

        JPanel level11Panel = new JPanel(); //west border
        level1Panel.add(level11Panel, BorderLayout.WEST);
        level11Panel.setLayout(new BoxLayout(level11Panel, BoxLayout.Y_AXIS));

        timePanel = new JPanel(); //contains time until next event
        level11Panel.add(timePanel);
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.Y_AXIS));

        JPanel dummyPanel = new JPanel();
        level11Panel.add(dummyPanel);

        JPanel level112Panel = new JPanel(); //contains button toolbar
        level11Panel.add(level112Panel);
        level112Panel.setLayout(new BoxLayout(level112Panel, BoxLayout.Y_AXIS));


        installSensorsButton = new JButton("Install Sensors");
        level112Panel.add(installSensorsButton);

        activateSensorsButton = new JButton("Activate Sensors");
        level112Panel.add(activateSensorsButton);

        setMalfunctionButton = new JButton("Set Malfunction");
        level112Panel.add(setMalfunctionButton);

        armSensorsButton = new JButton("Arm Sensors");
        level112Panel.add(armSensorsButton);

        triggerSensorsButton = new JButton("Trigger Sensors");
        //level112Panel.add(triggerSensorsButton);

        resetSensorsButton = new JButton("Reset Sensors");
        level112Panel.add(resetSensorsButton);

        advancedArmingMenuButton = new JButton("Advanced Arming Menu");
        level112Panel.add(advancedArmingMenuButton);

        managePasswordButton = new JButton("Edit Password");
        level112Panel.add(managePasswordButton);

        changeCustomerButton = new JButton("Customer Data");
        level112Panel.add(changeCustomerButton);

        printInvoiceButton = new JButton("Print Invoice");
        level112Panel.add(printInvoiceButton);

        JPanel level14Panel = new JPanel();
        level1Panel.add(level14Panel, BorderLayout.NORTH);
        String whichSystem;
        if(currentSystem==CurrentSystem.FIREALARMSYSTEM)
            whichSystem="Fire Alarm Sensor System";
        else
            whichSystem="Security Sensor System";
        currentSystemLabel = new JLabel(whichSystem);
        level14Panel.add(currentSystemLabel);

        JPanel level15Panel = new JPanel();
        level1Panel.add(level15Panel, BorderLayout.SOUTH);
        currentStatusLabel = new JLabel("No Systems Installed");
        level15Panel.add(currentStatusLabel);

        JPanel level12Panel = new JPanel(); //center region
        level1Panel.add(level12Panel, BorderLayout.CENTER);
        level12Panel.setLayout(new GridLayout(3,3));

        roomButtons = new ArrayList<JButton>();

        room1Panel = new Room1FloorPlan();
        room1Panel.setBackground(Color.white);
        room1Panel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(room1Panel);
        room1Panel.setLayout(new GridBagLayout());
        room1Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton room1Button = new JButton("Room 1");
        roomButtons.add(room1Button);
        room1Panel.add(room1Button);

        room2Panel = new Room2FloorPlan();
        room2Panel.setBackground(Color.white);
        room2Panel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(room2Panel);
        room2Panel.setLayout(new GridBagLayout());
        room2Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton room2Button = new JButton("Room 2");
        roomButtons.add(room2Button);
        room2Panel.add(room2Button);

        room3Panel = new Room3FloorPlan();
        room3Panel.setBackground(Color.white);
        room3Panel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(room3Panel);
        room3Panel.setLayout(new GridBagLayout());
        room3Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton room3Button = new JButton("Room 3");
        roomButtons.add(room3Button);
        room3Panel.add(room3Button);

        hallwayWestPanel = new HallwayWestFloorPlan();
        hallwayWestPanel.setBackground(Color.white);
        hallwayWestPanel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(hallwayWestPanel);
        hallwayWestPanel.setLayout(new GridBagLayout());
        hallwayWestPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton hallwayEastButton = new JButton("Hallway West");
        roomButtons.add(hallwayEastButton);
        hallwayWestPanel.add(hallwayEastButton);

        hallwayCenterPanel = new HallwayCenterFloorPlan();
        hallwayCenterPanel.setBackground(Color.white);
        hallwayCenterPanel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(hallwayCenterPanel);
        hallwayCenterPanel.setLayout(new GridBagLayout());
        hallwayCenterPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        hallwayEastPanel = new HallwayEastFloorPlan();
        hallwayEastPanel.setBackground(Color.white);
        hallwayEastPanel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(hallwayEastPanel);
        hallwayEastPanel.setLayout(new GridBagLayout());
        hallwayEastPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton hallwayWestButton = new JButton("Hallway East");
        roomButtons.add(hallwayWestButton);
        hallwayEastPanel.add(hallwayWestButton);

        room4Panel = new Room4FloorPlan();
        room4Panel.setBackground(Color.white);
        room4Panel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(room4Panel);
        room4Panel.setLayout(new GridBagLayout());
        room4Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton room4Button = new JButton("Room 4");
        roomButtons.add(room4Button);
        room4Panel.add(room4Button);

        room5Panel = new Room5FloorPlan();
        room5Panel.setBackground(Color.white);
        room5Panel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(room5Panel);
        room5Panel.setLayout(new GridBagLayout());
        room5Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton room5Button = new JButton("Room 5");
        roomButtons.add(room5Button);
        room5Panel.add(room5Button);

        room6Panel = new Room6FloorPlan();
        room6Panel.setBackground(Color.white);
        room6Panel.setPreferredSize(new Dimension(200,200));
        level12Panel.add(room6Panel);
        room6Panel.setLayout(new GridBagLayout());
        room6Panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton room6Button = new JButton("Room 6");
        roomButtons.add(room6Button);
        room6Panel.add(room6Button);

        JPanel level13Panel = new JPanel(); //contains switch system and installation buttons
        level13Panel.setPreferredSize(new Dimension(340,400));
        level1Panel.add(level13Panel, BorderLayout.EAST);
        level13Panel.setLayout(new BoxLayout(level13Panel, BoxLayout.Y_AXIS));

        switchSystemButton = new JButton("Switch System");
        level13Panel.add(switchSystemButton);

        installSecurityButton = new JButton("Install Security System");
        level13Panel.add(installSecurityButton);

        installFireAlarmButton = new JButton("Install Fire Alarm System");
        level13Panel.add(installFireAlarmButton);

        sprinklersButton = new JButton("Sprinklers");
        level13Panel.add(sprinklersButton);

        level13Panel.add(Box.createVerticalGlue());

        JPanel level131Panel = new JPanel();
        level13Panel.add(level131Panel);
        simulateComboBox = new JComboBox(new String[] {"Room 1", "Room 2", "Room 3", "Hallway West", "Hallway East",
                                            "Room 4", "Room 5", "Room 6"});
        level131Panel.add(new JScrollPane(simulateComboBox));

        simulateButton = new JButton("Simulate event");
        level131Panel.add(simulateButton);

        level13Panel.add(Box.createVerticalGlue());

        labelPanel = new JPanel();
        level13Panel.add(labelPanel);
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(new JLabel("Green is installed"));
        labelPanel.add(new JLabel("Yellow is activated"));
        labelPanel.add(new JLabel("Pink is armed"));
        labelPanel.add(new JLabel("Red is triggered"));
        labelPanel.add(new JLabel("Blue is sprinklers on"));


        level13Panel.add(Box.createVerticalGlue());

        doneButton = new JButton("Done");
        level13Panel.add(doneButton);
    }

    /**
     * Getter for the type of the current state of system, either Fire Alarm or Security
     * @return an enum describing current system viewed
     */
    public CurrentSystem getCurrentSystemName(){
        return currentSystem;
    }

    /**
     * Getter for the SensorSystem of the window's system that is currently being viewed
     * @return SensorSystem for the current system being viewed
     */
    public SensorSystem getCurrentSystem(){
        SensorSystem mySystem;
        if(currentSystem==CurrentSystem.FIREALARMSYSTEM)
            mySystem =myInstallationManager.getFireAlarmSystem();
        else
            mySystem =myInstallationManager.getSecuritySensorSystem();
        return mySystem;
    }

    /**
     * Getter of the status label for the window
     * @return the status label, which displays program status
     */
    public JLabel getCurrentStatusLabel() {
        return currentStatusLabel;
    }

    /**
     * Getter for the customer data
     * @return customer data
     */
    public Customer getMyCustomer() {
        return myCustomer;
    }

    /**
     * Getter for installation manager, which contains the two systems of the program
     * @return installation manager of the program
     */
    public InstallationManager getMyInstallationManager() {
        return myInstallationManager;
    }

    /**
     * pack the frame and make it visible
     */
    public void finishFrame(){
        roomsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        roomsFrame.pack();
        roomsFrame.setVisible(true);
    }


    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
            public void run() {

                GraphicalRooms testGraphicalRooms = new GraphicalRooms(
                        CurrentSystem.FIREALARMSYSTEM, new InstallationManager(),
                        new Customer("001", "Santa Clara University",
                        "500 El Camino Real", "911",
                        "408-277-8900", "registrar@scu.edu",
                                "04/04/2017-04/04/2018"));
                testGraphicalRooms.finishFrame();
            }
        });
    }
}
