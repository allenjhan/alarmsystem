package ViewGraphical;

/**
 * Created by ahan on 6/4/17.
 */
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.*;
import Model.*;

public class GraphicalAdvancedArmingMenu {
    private GraphicalArmSystem mainView;
    private SensorSystem mySystem;
    private CurrentSystem nameOfCurrentSystem;

    //private AbstractTableModel standaloneModel;
    //private AbstractTableModel dailyModel;

    /**
     * The constructor of the Advanced Arming Menu's view. Called after selecting "Advanced Arming Menu"
     * from the main window of the program.
     * @param mainView the view object for the main window of the program
     */
    public GraphicalAdvancedArmingMenu(GraphicalArmSystem mainView) {
        this.mainView = mainView;
        this.mySystem = mainView.getCurrentSystem();

        if (mySystem == mainView.getMyInstallationManager().getFireAlarmSystem())
            nameOfCurrentSystem = CurrentSystem.FIREALARMSYSTEM;
        else
            nameOfCurrentSystem = CurrentSystem.SECURITYSYSTEM;

        createMenu();
    }

    /**
     * Create the GUI of the Advanced Arming Menu.
     */
    public void createMenu(){
        CurrentSystem currentSystemName = mainView.getCurrentSystemName();
        String frameTitle;
        if(currentSystemName==CurrentSystem.FIREALARMSYSTEM)
            frameTitle = "Fire Alarm System Advanced Arming Menu";
        else
            frameTitle = "Security System Advanced Arming Menu";

        JFrame myFrame = new JFrame(frameTitle);
        JPanel level1Panel = new JPanel();
        myFrame.add(level1Panel);
        level1Panel.setLayout(new BoxLayout(level1Panel, BoxLayout.Y_AXIS));

        level1Panel.add(new JLabel("All Events"));
        /*
        final String standaloneTitles[] = new String[]{"Date","Time","Event Type", "Active Sensors","Delete"};
        standaloneModel = new AbstractTableModel(){
            public int getColumnCount(){
                return 5;
            }

            public int getRowCount(){
                return mySystem.getScheduler().getStandaloneEvents().size();
            }

            public Object getValueAt(int row, int col) {
                switch (col) {
                    case 0:
                        LocalDate currentDate = mySystem.getScheduler().getStandaloneEvents().get(row).getTime().toLocalDate();
                        return currentDate.toString();
                    case 1:
                        LocalTime currentTime = mySystem.getScheduler().getStandaloneEvents().get(row).getTime().toLocalTime();
                        return currentTime.toString();
                    case 2:
                        EventType currentType = mySystem.getScheduler().getStandaloneEvents().get(row).getEventType();
                        if (currentType == EventType.ARM)
                            return "Arm";
                        else if (currentType == EventType.DISARM)
                            return "Disarm";
                        else
                            return "Neither";
                    case 3:
                        return "Set";
                    case 4:
                        return "Delete";
                    default:
                        return null;
                }
            }

            public String getColumnName(int c){
                return standaloneTitles[c];
            }

            public boolean isCellEditable(int row, int column){
                if (column==3 || column ==4)
                    return true;
                else
                    return false;
            }
        };
        */
        JTable standaloneTable = new JTable(mySystem.getScheduler().getStandaloneModel());
        level1Panel.add(new JScrollPane(standaloneTable));
        standaloneTable.setFillsViewportHeight(true);

        Action setActStandalone = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                int modelRow = Integer.valueOf(e.getActionCommand());
                GraphicalEventActivation myView = new GraphicalEventActivation(nameOfCurrentSystem,
                        mainView.getMyInstallationManager(),
                        mainView.getMyCustomer(),
                        mySystem.getScheduler().getStandaloneEvents().get(modelRow));
            }
        };
        ButtonColumn standaloneActSensors = new ButtonColumn(standaloneTable, setActStandalone, 3);

        Action deleteStandalone = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                if(mySystem.getScheduler().getStandaloneEvents().get(modelRow).isDailyArmingEvent())
                    JOptionPane.showMessageDialog(null, "Use the scheduler to remove", "Error", JOptionPane.INFORMATION_MESSAGE);
                else {
                    mySystem.getScheduler().getStandaloneEvents().remove(modelRow);
                    mySystem.getScheduler().getStandaloneModel().fireTableDataChanged();
                }
            }
        };
        ButtonColumn standaloneDeleteColumn = new ButtonColumn(standaloneTable, deleteStandalone, 4);


        JButton addTimerEventButton = new JButton("Add Arming Event by Timer");
        level1Panel.add(addTimerEventButton);
        final GraphicalAdvancedArmingMenu myMenu = this;
        addTimerEventButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GraphicalArmTimer myTimer = new GraphicalArmTimer(myMenu, mainView);
            }
        });

        level1Panel.add(new JLabel("Daily Scheduled Events"));
        final String dailyTitles[] = new String[]{"Time","Event Type", "Active Sensors","Delete"};
        /*
        dailyModel = new AbstractTableModel() {
            public int getColumnCount() {
                return 4;
            }

            public int getRowCount() {
                return mySystem.getScheduler().getDailySchedule().size();
            }

            public Object getValueAt(int row, int col) {
                switch (col) {
                    case 0:
                        LocalTime currentTime;
                        currentTime = mySystem.getScheduler().getDailySchedule().get(row).getTime();
                        return currentTime.toString();
                    case 1:
                        EventType currentType = mySystem.getScheduler().getDailySchedule().get(row).getEventType();
                        if (currentType == EventType.ARM)
                            return "Arm";
                        else if (currentType == EventType.DISARM)
                            return "Disarm;";
                        else
                            return "Neither";
                    case 2:
                        return "Set";
                    case 3:
                        return "Delete";
                    default:
                        return null;
                }
            }

            public String getColumnName(int c) {
                return dailyTitles[c];
            }

            public boolean isCellEditable(int row, int column){
                if (column==2 || column ==3)
                    return true;
                else
                    return false;
            }
        };
        */
        JTable dailyTable = new JTable(mySystem.getScheduler().getDailyModel());
        level1Panel.add(new JScrollPane(dailyTable));
        dailyTable.setFillsViewportHeight(true);

        Action setActDaily = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                int modelRow = Integer.valueOf(e.getActionCommand());
                GraphicalEventActivation myView = new GraphicalEventActivation(nameOfCurrentSystem,
                        mainView.getMyInstallationManager(),
                        mainView.getMyCustomer(),
                        mySystem.getScheduler().getDailySchedule().get(modelRow));
            }
        };
        ButtonColumn dailyActSensors = new ButtonColumn(dailyTable, setActDaily, 2);

        Action deleteDaily = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int modelRow = Integer.valueOf(e.getActionCommand());
                DailyArmingEvent toRemove = mySystem.getScheduler().getDailySchedule().remove(modelRow);
                for(ArmingEvent currentEvent:mySystem.getScheduler().getStandaloneEvents()){
                    //if(currentEvent.getEventType() == toRemove.getEventType() &&
                    //        currentEvent.getTime().toLocalTime().equals(toRemove.getTime()) &&
                    //        currentEvent.isPhoneCall() == toRemove.isPhoneCall()
                    // ){
                    if(currentEvent.getActivateArray() == toRemove.getActivateArray()){
                        mySystem.getScheduler().getStandaloneEvents().delete(currentEvent);
                        break;
                    }
                }

                mySystem.getScheduler().getDailyModel().fireTableDataChanged();
                mySystem.getScheduler().getStandaloneModel().fireTableDataChanged();
            }
        };
        ButtonColumn dailyDelete = new ButtonColumn(dailyTable, deleteDaily, 3);

        JButton addArmingEventByScheduler = new JButton("Add Arming Event by Scheduler");
        level1Panel.add(addArmingEventByScheduler);
        addArmingEventByScheduler.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                GraphicalArmScheduler myScheduler = new GraphicalArmScheduler(myMenu, mainView);
            }
        });

        myFrame.pack();
        myFrame.setVisible(true);
    }

    /**
     * Getter for the system, either Fire Alarm or Security, of the Advanced Arming Menu window
     * @return
     */
    public SensorSystem getMySystem() {
        return mySystem;
    }

    /**
     * Getter for the system type, either Fire Alarm or Security, of the current Advanced Arming Menu Window.
     * @return
     */
    public CurrentSystem getNameOfCurrentSystem() {
        return nameOfCurrentSystem;
    }

    /*
    public AbstractTableModel getStandaloneModel() {
        return standaloneModel;
    }

    public AbstractTableModel getDailyModel() {
        return dailyModel;
    }
    */

    /**
     * The class which is used to create a column of buttons in a JTable.
     */
    public class ButtonColumn extends AbstractCellEditor
            implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
    {
        private JTable table;
        private Action action;
        private int mnemonic;
        private Border originalBorder;
        private Border focusBorder;

        private JButton renderButton;
        private JButton editButton;
        private Object editorValue;
        private boolean isButtonColumnEditor;

        /**
         *  Create the ButtonColumn to be used as a renderer and editor. The
         *  renderer and editor will automatically be installed on the TableColumn
         *  of the specified column.
         *
         *  @param table the table containing the button renderer/editor
         *  @param action the Action to be invoked when the button is invoked
         *  @param column the column to which the button renderer/editor is added
         */
        public ButtonColumn(JTable table, Action action, int column)
        {
            this.table = table;
            this.action = action;

            renderButton = new JButton();
            editButton = new JButton();
            editButton.setFocusPainted( false );
            editButton.addActionListener( this );
            originalBorder = editButton.getBorder();
            setFocusBorder( new LineBorder(Color.BLUE) );

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
            table.addMouseListener( this );
        }


        /**
         *  Get foreground color of the button when the cell has focus
         *
         *  @return the foreground color
         */
        public Border getFocusBorder()
        {
            return focusBorder;
        }

        /**
         *  The foreground color of the button when the cell has focus
         *
         *  @param focusBorder the foreground color
         */
        public void setFocusBorder(Border focusBorder)
        {
            this.focusBorder = focusBorder;
            editButton.setBorder( focusBorder );
        }

        public int getMnemonic()
        {
            return mnemonic;
        }

        /**
         *  The mnemonic to activate the button when the cell has focus
         *
         *  @param mnemonic the mnemonic
         */
        public void setMnemonic(int mnemonic)
        {
            this.mnemonic = mnemonic;
            renderButton.setMnemonic(mnemonic);
            editButton.setMnemonic(mnemonic);
        }

        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column)
        {
            if (value == null)
            {
                editButton.setText( "" );
                editButton.setIcon( null );
            }
            else if (value instanceof Icon)
            {
                editButton.setText( "" );
                editButton.setIcon( (Icon)value );
            }
            else
            {
                editButton.setText( value.toString() );
                editButton.setIcon( null );
            }

            this.editorValue = value;
            return editButton;
        }

        public Object getCellEditorValue()
        {
            return editorValue;
        }

        //
//  Implement TableCellRenderer interface
//
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            if (isSelected)
            {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            }
            else
            {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }

            if (hasFocus)
            {
                renderButton.setBorder( focusBorder );
            }
            else
            {
                renderButton.setBorder( originalBorder );
            }

//		renderButton.setText( (value == null) ? "" : value.toString() );
            if (value == null)
            {
                renderButton.setText( "" );
                renderButton.setIcon( null );
            }
            else if (value instanceof Icon)
            {
                renderButton.setText( "" );
                renderButton.setIcon( (Icon)value );
            }
            else
            {
                renderButton.setText( value.toString() );
                renderButton.setIcon( null );
            }

            return renderButton;
        }

        //
//  Implement ActionListener interface
//
	/*
	 *	The button has been pressed. Stop editing and invoke the custom Action
	 */
        public void actionPerformed(ActionEvent e)
        {
            int row = table.convertRowIndexToModel( table.getEditingRow() );
            fireEditingStopped();

            //  Invoke the Action

            ActionEvent event = new ActionEvent(
                    table,
                    ActionEvent.ACTION_PERFORMED,
                    "" + row);
            action.actionPerformed(event);
        }

        //
//  Implement MouseListener interface
//
	/*
	 *  When the mouse is pressed the editor is invoked. If you then then drag
	 *  the mouse to another cell before releasing it, the editor is still
	 *  active. Make sure editing is stopped when the mouse is released.
	 */
        public void mousePressed(MouseEvent e)
        {
            if (table.isEditing()
                    &&  table.getCellEditor() == this)
                isButtonColumnEditor = true;
        }

        public void mouseReleased(MouseEvent e)
        {
            if (isButtonColumnEditor
                    &&  table.isEditing())
                table.getCellEditor().stopCellEditing();

            isButtonColumnEditor = false;
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }


}


