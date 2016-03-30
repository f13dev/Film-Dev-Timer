/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DevTimer;

import java.awt.CardLayout;
import java.awt.Color;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import sun.audio.*;


/**
 *
 * @author jym
 */
public class DevTimerMain extends javax.swing.JFrame {
    
    static String[] filmArray;
    static int filmRows = 125;
    int selectedDevTime;
    int tempDevTime;
    int minDevTime;
    int secDevTime;
    public int count = -1;
    int timePreWash;
    int timeDeveloper;
    int timeStopBath;
    int timeFixer;
    int timeHypoClearing;
    int timeFinalWash;
    int timeWettingAgent;
    int countdown;
    int minutes;
    int seconds;
    int counter;
    
    static void createFilmArray() {
        filmArray = new String[filmRows];
        
        Scanner scanLn = null;
        int Rowc = 0;
        int Row = 0;
        String InputLine = "";
        String filmFileName;
        
        filmFileName = "filmdb.csv";
        
        System.out.println("\n****** Setup Film Array ******");
        
        try {
            scanLn = new Scanner(new BufferedReader(new FileReader(filmFileName)));
            
            while (scanLn.hasNextLine()) {
                InputLine = scanLn.nextLine();
                filmArray [Rowc] = InputLine;
                Rowc++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }        
    }
    
    static String xStrPath;
    static String[][] myArray;
    static int rows = 4261;
    public String filmSet;
    
    static void setUpMyCSVArray() {
        myArray = new String[rows][5];        
        
        Scanner scanLn = null;
        int Rowc = 0;
        int Row = 0;
        int Colc = 0;
        int Col = 0;
        String InputLine = "";
        double xnum = 0;
        String xfileLocation;
        
        xfileLocation = "masterdb.csv";
        
        System.out.println("\n****** Setup Array ******");
        
        try {
            scanLn = new Scanner(new BufferedReader(new FileReader(xfileLocation)));
            
            while (scanLn.hasNextLine()) {
                InputLine = scanLn.nextLine();
                String[] InArray = InputLine.split(",");
                for (int x = 0; x < InArray.length; x++) {
                    myArray [Rowc] [x] = InArray[x];
                }
                Rowc++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // set variables for the settings array
    static String[] settingsArray;
    int preWash;
    int stopBath;
    int fixer;
    int hypoClearing;
    int finalWash;
    int wettingAgent;
    static int minPreWash;
    static int secPreWash;
    static int minDeveloper;
    static int secDeveloper;
    static int minStopBath;
    static int secStopBath;
    static int minFixer;
    static int secFixer;
    static int minHypoClearing;
    static int secHypoClearing;
    static int minFinalWash;
    static int secFinalWash;
    static int minWettingAgent;
    static int secWettingAgent;
    static int minInitialAgitation;
    static int secInitialAgitation;
    static int minThenAgitation;
    static int secThenAgitation;
    int clickPlayed;
    Timer timer;
    
    static void createSettingsArray() {
        settingsArray = new String[9];
        
        Scanner scanLn = null;
        int Rowc = 0;
        int Row = 0;
        String InputLine = "";
        String settingsFileName;
        
        settingsFileName = "usersettings.csv";
        
        System.out.println("\n****** Setup Settings Array ******");
        
        try {
            scanLn = new Scanner(new BufferedReader(new FileReader(settingsFileName)));
            
            while (scanLn.hasNextLine()) {
                InputLine = scanLn.nextLine();
                settingsArray [Rowc] = InputLine;
                Rowc++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }     
        
        // Store the values from user settings in variables
        // Work out the number of whole pre wash minutes
        minPreWash = Integer.parseInt(settingsArray[0]) / 60;
        // Work out the remaining pre wash seconds
        secPreWash = Integer.parseInt(settingsArray[0]) - (minPreWash * 60);
        // Work out the number of whole developer minutes
        minDeveloper = Integer.parseInt(settingsArray[1]) / 60;
        // Work out the remaining developer seconds
        secDeveloper = Integer.parseInt(settingsArray[1]) - (minDeveloper * 60);
        // Work out the number of whole stop bath minutes
        minStopBath = Integer.parseInt(settingsArray[2]) / 60;
        // Work out the remaining stop bath seconds
        secStopBath = Integer.parseInt(settingsArray[2]) - (minStopBath * 60);
        // Work out the number of whole fix minutes
        minFixer = Integer.parseInt(settingsArray[3]) / 60;
        // Work out the remaining fix seconds
        secFixer = Integer.parseInt(settingsArray[3]) - (minFixer * 60);
        // Work out the number of whole hypo clearing minutes
        minHypoClearing = Integer.parseInt(settingsArray[4]) / 60;
        // Work out the remaining hypo clearing seconds
        secHypoClearing = Integer.parseInt(settingsArray[4]) - (minHypoClearing * 60);
        // Work out the number of whole final wash minutes
        minFinalWash = Integer.parseInt(settingsArray[5]) / 60;
        // Work out the remaining final wash seconds
        secFinalWash = Integer.parseInt(settingsArray[5]) - (minFinalWash * 60);
        // Work out the number of whole wetting agent minutes
        minWettingAgent = Integer.parseInt(settingsArray[6]) / 60;
        // Work out the remaining wetting agent seconds
        secWettingAgent = Integer.parseInt(settingsArray[6]) - (minWettingAgent * 60);
        // Work out the number of whole initial agitation minutes
        minInitialAgitation = Integer.parseInt(settingsArray[7]) / 60;
        secInitialAgitation = Integer.parseInt(settingsArray[7]) - (minInitialAgitation * 60);
        // Work out the number of seconds agitation per minute
        secThenAgitation = Integer.parseInt(settingsArray[8]);
        

    }
    
    public void settingsComboBoxes() {
        // Set the dropdown boxes to the values from the user settings file
        DefaultOptions.preWashMin.setSelectedIndex(minPreWash);
        DefaultOptions.preWashSec.setSelectedIndex(secPreWash);
        DefaultOptions.developerMin.setSelectedIndex(minDeveloper);
        DefaultOptions.developerSec.setSelectedIndex(secDeveloper);
        DefaultOptions.stopBathMin.setSelectedIndex(minStopBath);
        DefaultOptions.stopBathSec.setSelectedIndex(secStopBath);   
        DefaultOptions.fixerMin.setSelectedIndex(minFixer);
        DefaultOptions.fixerSec.setSelectedIndex(secFixer);        
        DefaultOptions.hypoClearingMin.setSelectedIndex(minHypoClearing);
        DefaultOptions.hypoClearingSec.setSelectedIndex(secHypoClearing); 
        DefaultOptions.finalWashMin.setSelectedIndex(minFinalWash);
        DefaultOptions.finalWashSec.setSelectedIndex(secFinalWash);
        DefaultOptions.wettingAgentMin.setSelectedIndex(minWettingAgent);
        DefaultOptions.wettingAgentSec.setSelectedIndex(secWettingAgent);
        DefaultOptions.initialAgitationMin.setSelectedIndex(minInitialAgitation);
        DefaultOptions.initialAgitationSec.setSelectedIndex(secInitialAgitation);
        DefaultOptions.thenSec.setSelectedIndex(secThenAgitation);
        
    } 
    
    public void setComboBoxes() {
        // Set the dropdown boxes to the values from the user settings file
        prewashMin.setSelectedIndex(minPreWash);
        prewashSec.setSelectedIndex(secPreWash);
        devMin.setSelectedIndex(minDeveloper);
        devSec.setSelectedIndex(secDeveloper);
        stopMin.setSelectedIndex(minStopBath);
        stopSec.setSelectedIndex(secStopBath);
        fixMin.setSelectedIndex(minFixer);
        fixSec.setSelectedIndex(secFixer);   
        hypoMin.setSelectedIndex(minHypoClearing);
        hypoSec.setSelectedIndex(secHypoClearing);        
        washMin.setSelectedIndex(minFinalWash);
        washSec.setSelectedIndex(secFinalWash); 
        wetMin.setSelectedIndex(minWettingAgent);
        wetSec.setSelectedIndex(secWettingAgent);   
    } 

    /**
     * Creates new form DevTimerMain
     */
    public DevTimerMain() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        CardHolder = new javax.swing.JPanel();
        WelcomePanel = new javax.swing.JPanel();
        welcomeManualBtn = new javax.swing.JButton();
        welcomeDatabaseBtn = new javax.swing.JButton();
        DatabasePanel = new javax.swing.JPanel();
        ManualLabel1 = new javax.swing.JLabel();
        filmComboBox = new javax.swing.JComboBox();
        developerComboBox = new javax.swing.JComboBox();
        filmLbl = new javax.swing.JLabel();
        developerLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        detailTextArea = new javax.swing.JTextArea();
        btnFilm = new javax.swing.JButton();
        btnDeveloper = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnStartDb = new javax.swing.JButton();
        ManualPanel = new javax.swing.JPanel();
        ManualLabel = new javax.swing.JLabel();
        prewashMin = new javax.swing.JComboBox();
        prewashSec = new javax.swing.JComboBox();
        devMin = new javax.swing.JComboBox();
        devSec = new javax.swing.JComboBox();
        stopMin = new javax.swing.JComboBox();
        stopSec = new javax.swing.JComboBox();
        fixMin = new javax.swing.JComboBox();
        fixSec = new javax.swing.JComboBox();
        hypoMin = new javax.swing.JComboBox();
        hypoSec = new javax.swing.JComboBox();
        washMin = new javax.swing.JComboBox();
        washSec = new javax.swing.JComboBox();
        wetMin = new javax.swing.JComboBox();
        wetSec = new javax.swing.JComboBox();
        prewashLabel = new javax.swing.JLabel();
        developerLabel = new javax.swing.JLabel();
        stopLabel = new javax.swing.JLabel();
        fixerLabel = new javax.swing.JLabel();
        hypoLabel = new javax.swing.JLabel();
        washLabel = new javax.swing.JLabel();
        wettingLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnManualStart = new javax.swing.JButton();
        timerPanel = new javax.swing.JPanel();
        lblMinutes = new javax.swing.JLabel();
        lblSeconds = new javax.swing.JLabel();
        lblProcessIndicator = new javax.swing.JLabel();
        btnNextStep = new javax.swing.JButton();
        finalPanel = new javax.swing.JPanel();
        lblFinalTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnStartAgain = new javax.swing.JButton();
        DTMenuBar = new javax.swing.JMenuBar();
        DTMFile = new javax.swing.JMenu();
        DTMFBackToStart = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        DTMOSetDefaults = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        DTMHHelpFile = new javax.swing.JMenuItem();
        DTMHAbout = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        DTMFExit = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dev Timer - TheVDM.com");
        setMaximumSize(new java.awt.Dimension(800, 500));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        CardHolder.setBackground(new java.awt.Color(1, 1, 1));
        CardHolder.setLayout(new java.awt.CardLayout());

        WelcomePanel.setBackground(new java.awt.Color(1, 1, 1));

        welcomeManualBtn.setBackground(new java.awt.Color(1, 1, 1));
        welcomeManualBtn.setFont(new java.awt.Font("DejaVu Sans", 0, 48)); // NOI18N
        welcomeManualBtn.setForeground(new java.awt.Color(254, 254, 254));
        welcomeManualBtn.setText("Manually set developing times");
        welcomeManualBtn.setBorder(new javax.swing.border.MatteBorder(null));
        welcomeManualBtn.setBorderPainted(false);
        welcomeManualBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welcomeManualBtnActionPerformed(evt);
            }
        });

        welcomeDatabaseBtn.setBackground(new java.awt.Color(1, 1, 1));
        welcomeDatabaseBtn.setFont(new java.awt.Font("DejaVu Sans", 0, 48)); // NOI18N
        welcomeDatabaseBtn.setForeground(new java.awt.Color(254, 254, 254));
        welcomeDatabaseBtn.setText("Select film from database");
        welcomeDatabaseBtn.setBorder(new javax.swing.border.MatteBorder(null));
        welcomeDatabaseBtn.setBorderPainted(false);
        welcomeDatabaseBtn.setDefaultCapable(false);
        welcomeDatabaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                welcomeDatabaseBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout WelcomePanelLayout = new javax.swing.GroupLayout(WelcomePanel);
        WelcomePanel.setLayout(WelcomePanelLayout);
        WelcomePanelLayout.setHorizontalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WelcomePanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(welcomeDatabaseBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomeManualBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
                .addContainerGap(783, Short.MAX_VALUE))
        );
        WelcomePanelLayout.setVerticalGroup(
            WelcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WelcomePanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(welcomeDatabaseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(welcomeManualBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        CardHolder.add(WelcomePanel, "WelcomeCard");

        DatabasePanel.setBackground(new java.awt.Color(1, 1, 1));

        ManualLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        ManualLabel1.setForeground(new java.awt.Color(226, 226, 226));
        ManualLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ManualLabel1.setText("Select film & Developer from the database");
        ManualLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        filmComboBox.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        filmComboBox.setModel(new javax.swing.DefaultComboBoxModel(filmArray));
        filmComboBox.setBorder(new javax.swing.border.MatteBorder(null));
        filmComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filmComboBoxActionPerformed(evt);
            }
        });

        developerComboBox.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        developerComboBox.setEnabled(false);
        developerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                developerComboBoxActionPerformed(evt);
            }
        });

        filmLbl.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        filmLbl.setForeground(new java.awt.Color(224, 224, 224));
        filmLbl.setText("Film");

        developerLbl.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        developerLbl.setForeground(new java.awt.Color(224, 224, 224));
        developerLbl.setText("Developer");

        jScrollPane1.setBorder(null);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(765, 202));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(765, 202));

        detailTextArea.setEditable(false);
        detailTextArea.setBackground(new java.awt.Color(1, 1, 1));
        detailTextArea.setColumns(20);
        detailTextArea.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        detailTextArea.setForeground(new java.awt.Color(226, 226, 226));
        detailTextArea.setRows(5);
        detailTextArea.setBorder(null);
        detailTextArea.setDisabledTextColor(new java.awt.Color(1, 1, 1));
        jScrollPane1.setViewportView(detailTextArea);

        btnFilm.setBackground(new java.awt.Color(1, 1, 1));
        btnFilm.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnFilm.setForeground(new java.awt.Color(225, 225, 225));
        btnFilm.setText(">>");
        btnFilm.setBorder(new javax.swing.border.MatteBorder(null));
        btnFilm.setBorderPainted(false);
        btnFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilmActionPerformed(evt);
            }
        });

        btnDeveloper.setBackground(new java.awt.Color(1, 1, 1));
        btnDeveloper.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnDeveloper.setForeground(new java.awt.Color(225, 225, 225));
        btnDeveloper.setText(">>");
        btnDeveloper.setBorder(new javax.swing.border.MatteBorder(null));
        btnDeveloper.setBorderPainted(false);
        btnDeveloper.setMaximumSize(new java.awt.Dimension(150, 40));
        btnDeveloper.setMinimumSize(new java.awt.Dimension(150, 40));
        btnDeveloper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeveloperActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(1, 1, 1));
        btnClear.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(225, 225, 225));
        btnClear.setText("Clear form");
        btnClear.setBorder(new javax.swing.border.MatteBorder(null));
        btnClear.setBorderPainted(false);
        btnClear.setMaximumSize(new java.awt.Dimension(150, 40));
        btnClear.setMinimumSize(new java.awt.Dimension(150, 40));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnStartDb.setBackground(new java.awt.Color(1, 1, 1));
        btnStartDb.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnStartDb.setForeground(new java.awt.Color(226, 226, 226));
        btnStartDb.setText("Start");
        btnStartDb.setBorder(new javax.swing.border.MatteBorder(null));
        btnStartDb.setBorderPainted(false);
        btnStartDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartDbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DatabasePanelLayout = new javax.swing.GroupLayout(DatabasePanel);
        DatabasePanel.setLayout(DatabasePanelLayout);
        DatabasePanelLayout.setHorizontalGroup(
            DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatabasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ManualLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(DatabasePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DatabasePanelLayout.createSequentialGroup()
                        .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DatabasePanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(filmLbl))
                            .addComponent(filmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70)
                        .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(developerLbl)
                            .addComponent(btnDeveloper, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(developerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(DatabasePanelLayout.createSequentialGroup()
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStartDb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(776, Short.MAX_VALUE))
        );
        DatabasePanelLayout.setVerticalGroup(
            DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatabasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ManualLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filmLbl)
                    .addComponent(developerLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filmComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(developerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeveloper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DatabasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStartDb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        CardHolder.add(DatabasePanel, "DatabaseCard");

        ManualPanel.setBackground(new java.awt.Color(1, 1, 1));

        ManualLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        ManualLabel.setForeground(new java.awt.Color(226, 226, 226));
        ManualLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ManualLabel.setText("Manual settings");
        ManualLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        prewashMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        prewashMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        prewashMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prewashMinActionPerformed(evt);
            }
        });

        prewashSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        prewashSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        devMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        devMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        devSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        devSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        stopMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        stopMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        stopSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        stopSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        fixMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        fixMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        fixSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        fixSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        hypoMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        hypoMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        hypoSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        hypoSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        hypoSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hypoSecActionPerformed(evt);
            }
        });

        washMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        washMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        washSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        washSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        wetMin.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        wetMin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        wetSec.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        wetSec.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        prewashLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        prewashLabel.setForeground(new java.awt.Color(225, 225, 225));
        prewashLabel.setText("Pre wash");

        developerLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        developerLabel.setForeground(new java.awt.Color(214, 214, 214));
        developerLabel.setText("Developer");

        stopLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        stopLabel.setForeground(new java.awt.Color(217, 217, 217));
        stopLabel.setText("Stop bath");

        fixerLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        fixerLabel.setForeground(new java.awt.Color(226, 226, 226));
        fixerLabel.setText("Fixer");

        hypoLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        hypoLabel.setForeground(new java.awt.Color(226, 226, 226));
        hypoLabel.setText("Hypo clearing");

        washLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        washLabel.setForeground(new java.awt.Color(226, 226, 226));
        washLabel.setText("Wash");

        wettingLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        wettingLabel.setForeground(new java.awt.Color(226, 226, 226));
        wettingLabel.setText("Wetting agent");

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(225, 225, 225));
        jLabel3.setText(":");

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(226, 226, 226));
        jLabel4.setText(":");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(224, 224, 224));
        jLabel5.setText(":");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(226, 226, 226));
        jLabel6.setText(":");

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(225, 225, 225));
        jLabel7.setText(":");

        jLabel8.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(226, 226, 226));
        jLabel8.setText(":");

        jLabel9.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(225, 225, 225));
        jLabel9.setText(":");

        btnManualStart.setBackground(new java.awt.Color(1, 1, 1));
        btnManualStart.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnManualStart.setForeground(new java.awt.Color(225, 225, 225));
        btnManualStart.setText("Start");
        btnManualStart.setBorder(new javax.swing.border.MatteBorder(null));
        btnManualStart.setBorderPainted(false);
        btnManualStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManualStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ManualPanelLayout = new javax.swing.GroupLayout(ManualPanel);
        ManualPanel.setLayout(ManualPanelLayout);
        ManualPanelLayout.setHorizontalGroup(
            ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ManualLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ManualPanelLayout.createSequentialGroup()
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ManualPanelLayout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prewashLabel)
                            .addComponent(stopLabel)
                            .addComponent(fixerLabel)
                            .addComponent(hypoLabel)
                            .addComponent(washLabel)
                            .addComponent(wettingLabel)
                            .addComponent(developerLabel))
                        .addGap(88, 88, 88)
                        .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(devMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prewashMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(stopMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fixMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hypoMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(washMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(wetMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ManualPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(3, 3, 3)
                                .addComponent(wetSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ManualPanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(3, 3, 3)
                                .addComponent(washSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ManualPanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(3, 3, 3)
                                .addComponent(hypoSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ManualPanelLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(3, 3, 3)
                                .addComponent(fixSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ManualPanelLayout.createSequentialGroup()
                                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(3, 3, 3)
                                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(stopSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(devSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prewashSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(ManualPanelLayout.createSequentialGroup()
                        .addGap(334, 334, 334)
                        .addComponent(btnManualStart, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(974, Short.MAX_VALUE))
        );
        ManualPanelLayout.setVerticalGroup(
            ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ManualPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(ManualLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prewashMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prewashSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prewashLabel)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(devSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(developerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stopMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stopLabel)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fixMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fixSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fixerLabel)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hypoMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hypoSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hypoLabel)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(washMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(washSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(washLabel)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ManualPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wetMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wetSec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wettingLabel)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addComponent(btnManualStart, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        CardHolder.add(ManualPanel, "ManualCard");

        timerPanel.setBackground(new java.awt.Color(1, 1, 1));

        lblMinutes.setFont(new java.awt.Font("DejaVu Sans", 1, 150)); // NOI18N
        lblMinutes.setForeground(new java.awt.Color(254, 254, 254));
        lblMinutes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinutes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DevTimer/minutes.jpg"))); // NOI18N
        lblMinutes.setText("10");
        lblMinutes.setBorder(null);
        lblMinutes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblSeconds.setFont(new java.awt.Font("DejaVu Sans", 1, 150)); // NOI18N
        lblSeconds.setForeground(new java.awt.Color(254, 254, 254));
        lblSeconds.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSeconds.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DevTimer/seconds.jpg"))); // NOI18N
        lblSeconds.setText("10");
        lblSeconds.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblProcessIndicator.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        lblProcessIndicator.setForeground(new java.awt.Color(254, 254, 254));
        lblProcessIndicator.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProcessIndicator.setText("Developer");

        btnNextStep.setBackground(new java.awt.Color(1, 1, 1));
        btnNextStep.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnNextStep.setForeground(new java.awt.Color(227, 227, 227));
        btnNextStep.setText("Next step");
        btnNextStep.setBorder(new javax.swing.border.MatteBorder(null));
        btnNextStep.setBorderPainted(false);
        btnNextStep.setMaximumSize(new java.awt.Dimension(250, 40));
        btnNextStep.setMinimumSize(new java.awt.Dimension(250, 40));
        btnNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextStepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout timerPanelLayout = new javax.swing.GroupLayout(timerPanel);
        timerPanel.setLayout(timerPanelLayout);
        timerPanelLayout.setHorizontalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblProcessIndicator, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, timerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNextStep, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(286, 286, 286))
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblMinutes)
                .addGap(0, 0, 0)
                .addComponent(lblSeconds)
                .addContainerGap(810, Short.MAX_VALUE))
        );
        timerPanelLayout.setVerticalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblProcessIndicator, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMinutes)
                    .addComponent(lblSeconds))
                .addGap(18, 18, 18)
                .addComponent(btnNextStep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        CardHolder.add(timerPanel, "TimerCard");

        finalPanel.setBackground(new java.awt.Color(1, 1, 1));

        lblFinalTitle.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        lblFinalTitle.setForeground(new java.awt.Color(254, 254, 254));
        lblFinalTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFinalTitle.setText("Complete");

        jScrollPane2.setBorder(null);

        jTextArea1.setBackground(new java.awt.Color(1, 1, 1));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(225, 225, 225));
        jTextArea1.setRows(5);
        jTextArea1.setText("You have now finished the developing process,\nit is now time to remove your film from the \ndeveloping tank and hang it to dry.");
        jTextArea1.setBorder(null);
        jScrollPane2.setViewportView(jTextArea1);

        btnStartAgain.setBackground(new java.awt.Color(1, 1, 1));
        btnStartAgain.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        btnStartAgain.setForeground(new java.awt.Color(225, 225, 225));
        btnStartAgain.setText("Start again");
        btnStartAgain.setBorder(new javax.swing.border.MatteBorder(null));
        btnStartAgain.setBorderPainted(false);
        btnStartAgain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartAgainActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout finalPanelLayout = new javax.swing.GroupLayout(finalPanel);
        finalPanel.setLayout(finalPanelLayout);
        finalPanelLayout.setHorizontalGroup(
            finalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFinalTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1562, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(finalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(183, 183, 183))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, finalPanelLayout.createSequentialGroup()
                        .addComponent(btnStartAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(319, 319, 319))))
        );
        finalPanelLayout.setVerticalGroup(
            finalPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(finalPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblFinalTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(btnStartAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        CardHolder.add(finalPanel, "finalCard");

        DTMFile.setText("Menu");

        DTMFBackToStart.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        DTMFBackToStart.setText("Back to the start");
        DTMFBackToStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTMFBackToStartActionPerformed(evt);
            }
        });
        DTMFile.add(DTMFBackToStart);
        DTMFile.add(jSeparator1);

        DTMOSetDefaults.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        DTMOSetDefaults.setText("Set default options");
        DTMOSetDefaults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTMOSetDefaultsActionPerformed(evt);
            }
        });
        DTMFile.add(DTMOSetDefaults);
        DTMFile.add(jSeparator2);

        DTMHHelpFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        DTMHHelpFile.setText("Help file");
        DTMHHelpFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTMHHelpFileActionPerformed(evt);
            }
        });
        DTMFile.add(DTMHHelpFile);

        DTMHAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        DTMHAbout.setText("About");
        DTMHAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTMHAboutActionPerformed(evt);
            }
        });
        DTMFile.add(DTMHAbout);
        DTMFile.add(jSeparator3);

        DTMFExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        DTMFExit.setText("Exit");
        DTMFExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DTMFExitActionPerformed(evt);
            }
        });
        DTMFile.add(DTMFExit);

        DTMenuBar.add(DTMFile);

        setJMenuBar(DTMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CardHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CardHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DTMFExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTMFExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_DTMFExitActionPerformed

    private void DTMHAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTMHAboutActionPerformed
        // TODO add your handling code here:
        new About().setVisible(true);
    }//GEN-LAST:event_DTMHAboutActionPerformed

    private void prewashMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prewashMinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prewashMinActionPerformed

    private void welcomeDatabaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welcomeDatabaseBtnActionPerformed
        // Switch to the database card
        CardLayout card = (CardLayout)CardHolder.getLayout();
        card.show(CardHolder, "DatabaseCard");
        createSettingsArray();
        
        // Clear the form for a fresh start
        developerComboBox.setEnabled(false);
        developerComboBox.removeAll();
        btnDeveloper.setVisible(false);
        filmComboBox.setEnabled(true);
        btnFilm.setVisible(true);
        detailTextArea.setText("");
        selectedDevTime = 9000;
        btnStartDb.setVisible(false);
        btnClear.setVisible(false);
    }//GEN-LAST:event_welcomeDatabaseBtnActionPerformed

    private void welcomeManualBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_welcomeManualBtnActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)CardHolder.getLayout();
        card.show(CardHolder, "ManualCard");
        createSettingsArray();
        setComboBoxes();
    }//GEN-LAST:event_welcomeManualBtnActionPerformed

    private void DTMFBackToStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTMFBackToStartActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)CardHolder.getLayout();
        card.show(CardHolder, "WelcomeCard");
    }//GEN-LAST:event_DTMFBackToStartActionPerformed

    private void filmComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filmComboBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_filmComboBoxActionPerformed

    private void developerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_developerComboBoxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_developerComboBoxActionPerformed

    private void btnFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilmActionPerformed
        // TODO add your handling code here:
        if (!filmComboBox.getSelectedItem().equals("Select a film")) {
            developerComboBox.removeAllItems();
            developerComboBox.setEnabled(true);
            filmComboBox.setEnabled(false);
            btnFilm.setVisible(false);
            btnDeveloper.setVisible(true);        
            developerComboBox.addItem("Select a developer");
            
            count = 0;
            while (count < rows) {
                if (filmComboBox.getSelectedItem().equals(myArray[count][0])) {
                    developerComboBox.addItem(myArray[count][1]);
                }
                count++;
            }
        }
    }//GEN-LAST:event_btnFilmActionPerformed

    private void btnDeveloperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeveloperActionPerformed
        // TODO add your handling code here:
        if (!developerComboBox.getSelectedItem().equals("Select a developer")) {
            developerComboBox.setEnabled(false);
            btnDeveloper.setVisible(false);
            btnStartDb.setVisible(true);
            btnClear.setVisible(true);

            count = 0;
            while (count < rows) {
                if ((filmComboBox.getSelectedItem().equals(myArray[count][0])) && (developerComboBox.getSelectedItem().equals(myArray[count][1]))){
                    selectedDevTime = count;
                }
                count++;
            }

            // Set the temporary dev time to the value from the database in seconds
            tempDevTime = Integer.parseInt(myArray[selectedDevTime][3]);
            // Work out the number of whole minutes
            minDevTime = (int)(tempDevTime / 60);
            // Work out the remaining seconds
            secDevTime = (int)(tempDevTime - (minDevTime * 60));

            detailTextArea.setText("Film: " + myArray[selectedDevTime][0] 
                    +"\nDeveloper: " + myArray[selectedDevTime][1] 
                    + "\nDeveloping time: " + minDevTime + ":" + secDevTime 
                    + "\nChemistry temperature: " + myArray[selectedDevTime][4] 
                    + "\n\nThe full process (these options can be changed from 'default settings' in the menu."
                    + "\nPre wash: " + minPreWash + ":" + secPreWash
                    + "\nDeveloper: " + minDevTime + ":" + secDevTime
                    + "\nStop bath: " + minStopBath + ":" + secStopBath
                    + "\nFixer: " + minFixer + ":" + secFixer
                    + "\nHypo clearing: " + minHypoClearing + ":" + secHypoClearing
                    + "\nFinal wash: " + minFinalWash + ":" + secFinalWash
                    + "\nWetting agent: " + minWettingAgent + ":" + secWettingAgent
            );
            detailTextArea.setCaretPosition(0);
        }
    }//GEN-LAST:event_btnDeveloperActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        developerComboBox.setEnabled(false);
        developerComboBox.removeAll();
        btnDeveloper.setVisible(false);
        filmComboBox.setEnabled(true);
        btnFilm.setVisible(true);
        detailTextArea.setText("");
        selectedDevTime = 9000;
        btnStartDb.setVisible(false);
        btnClear.setVisible(false);
    }//GEN-LAST:event_btnClearActionPerformed

    private void DTMOSetDefaultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTMOSetDefaultsActionPerformed
        // TODO add your handling code here:
        new DefaultOptions().setVisible(true);
        createSettingsArray();
        settingsComboBoxes();
    }//GEN-LAST:event_DTMOSetDefaultsActionPerformed

    private void hypoSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hypoSecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hypoSecActionPerformed

    /**
     *
     */
    private void setupTimer() {
        
        DTMFBackToStart.setEnabled(false);
        // Work out the next step of the developing process, change the
        // countdown to the desired time and clear the initial value.
        if (timePreWash > 0) {
            lblProcessIndicator.setText("Pre wash");
            countdown = timePreWash;
            timePreWash = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else if (timeDeveloper > 0) {
            lblProcessIndicator.setText("Developer");
            countdown = timeDeveloper;
            timeDeveloper = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else if (timeStopBath > 0) {
            lblProcessIndicator.setText("Stop bath");
            countdown = timeStopBath;
            timeStopBath = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else if (timeFixer > 0) {
            lblProcessIndicator.setText("Fixer");
            countdown = timeFixer;
            timeFixer = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else if (timeHypoClearing > 0) {
            lblProcessIndicator.setText("Hypo clearing");
            countdown = timeHypoClearing;
            timeHypoClearing = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else if (timeFinalWash > 0) {
            lblProcessIndicator.setText("FinalWash");
            countdown = timeFinalWash;
            timeFinalWash = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else if (timeWettingAgent > 0) {
            lblProcessIndicator.setText("Wetting agent");
            countdown = timeWettingAgent;
            timeWettingAgent = 0;
            // Start the timer
            TimeClass tc = new TimeClass(countdown, countdown);
            timer = new Timer(1000, tc);
            timer.start();
        } else {
            // If the developing process is complete go to the complete card
            CardLayout card = (CardLayout)CardHolder.getLayout();
            card.show(CardHolder, "finalCard");
            DTMFBackToStart.setEnabled(true);
        }
        
        btnNextStep.setVisible(false);
        minutes = countdown / 60;
        seconds = countdown - (minutes * 60);
        // Start the countdown
        lblMinutes.setText("" + minutes);
        lblSeconds.setText("" + seconds);
            
    }
 
    public class TimeClass implements ActionListener {
        int counter;
        int startTime;

        
        public TimeClass(int counter, int startTime) {
            this.counter = counter;
            this.startTime = startTime;
        }
             
        
        @Override
        public void actionPerformed(ActionEvent CountdownTimer) {
            counter--;
            
            // Work out the end time for the initial agitation
            int initAgitation = startTime - (minInitialAgitation * 60 + secInitialAgitation);

            if (counter > 0) {
                minutes = (int)(counter / 60);
                seconds = (int)(counter - (minutes * 60));
                
                lblMinutes.setText("" + minutes);
                lblSeconds.setText("" + seconds);
            //} else if (counter >= 1) {
            //    lblMinutes.setText("0");
            //    lblSeconds.setText("" + counter);
            } else if (counter == 0) {
                timer.stop();
                lblMinutes.setText("0");
                lblSeconds.setText("0");
                try {
                    InputStream playAlarm = new FileInputStream("alarm.wav");
                    AudioPlayer.player.start(playAlarm);
                } catch(Exception e) {}

                btnNextStep.setVisible(true);

            }
            
            if ((counter > initAgitation) || (secThenAgitation >= seconds)) {
                System.out.println(counter + "- " + initAgitation);
                try {
                    InputStream playClick = new FileInputStream("click.wav");
                    AudioPlayer.player.start(playClick);
                } catch(Exception e) {
                }
            }

        }
    }
    
    private void btnManualStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManualStartActionPerformed
        // TODO add your handling code here:
        // Set the time integers to be parsed to the timer
        timePreWash = Integer.parseInt((String) prewashMin.getSelectedItem()) * 60 + Integer.parseInt((String) prewashSec.getSelectedItem());
        timeDeveloper = Integer.parseInt((String) devMin.getSelectedItem()) * 60 + Integer.parseInt((String) devSec.getSelectedItem());
        timeStopBath = Integer.parseInt((String) stopMin.getSelectedItem()) * 60 + Integer.parseInt((String) stopSec.getSelectedItem());
        timeFixer = Integer.parseInt((String) fixMin.getSelectedItem()) * 60 + Integer.parseInt((String) fixSec.getSelectedItem());
        timeHypoClearing = Integer.parseInt((String) hypoMin.getSelectedItem()) * 60 + Integer.parseInt((String) hypoSec.getSelectedItem());
        timeFinalWash = Integer.parseInt((String) washMin.getSelectedItem()) * 60 + Integer.parseInt((String) washSec.getSelectedItem());
        timeWettingAgent = Integer.parseInt((String) wetMin.getSelectedItem()) * 60 + Integer.parseInt((String) wetSec.getSelectedItem());
        
        // Change to the timer card
        CardLayout card = (CardLayout)CardHolder.getLayout();
        card.show(CardHolder, "TimerCard");
        
        // Setup the timer
        setupTimer();  
    }//GEN-LAST:event_btnManualStartActionPerformed

    private void btnStartDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartDbActionPerformed
        // TODO add your handling code here:
        // Set the time integers to be parsed to the timer
        timePreWash = (minPreWash * 60) + secPreWash;
        timeDeveloper = tempDevTime;
        timeStopBath = (minStopBath * 60) + secStopBath;
        timeFixer = (minFixer * 60) + secFixer;
        timeHypoClearing = (minHypoClearing * 60) + secHypoClearing;
        timeFinalWash = (minFinalWash * 60) + secFinalWash;
        timeWettingAgent = (minWettingAgent * 60) + secWettingAgent;
        
        // Change to the timer card
        CardLayout card = (CardLayout)CardHolder.getLayout();
        card.show(CardHolder, "TimerCard");
        
        // Setup the timer
        setupTimer();  
    }//GEN-LAST:event_btnStartDbActionPerformed

    private void btnNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextStepActionPerformed
        // TODO add your handling code here:
        setupTimer();
    }//GEN-LAST:event_btnNextStepActionPerformed

    private void btnStartAgainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartAgainActionPerformed
        // TODO add your handling code here:
        CardLayout card = (CardLayout)CardHolder.getLayout();
        card.show(CardHolder, "WelcomeCard");
    }//GEN-LAST:event_btnStartAgainActionPerformed

    private void DTMHHelpFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DTMHHelpFileActionPerformed
        // TODO add your handling code here:
        new Help().setVisible(true);
    }//GEN-LAST:event_DTMHHelpFileActionPerformed

    /*    */
    /**/
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
        
        setUpMyCSVArray();
        createFilmArray();
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DevTimerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DevTimerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DevTimerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DevTimerMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DevTimerMain().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel CardHolder;
    private javax.swing.JMenuItem DTMFBackToStart;
    private javax.swing.JMenuItem DTMFExit;
    private javax.swing.JMenu DTMFile;
    private javax.swing.JMenuItem DTMHAbout;
    private javax.swing.JMenuItem DTMHHelpFile;
    private javax.swing.JMenuItem DTMOSetDefaults;
    private javax.swing.JMenuBar DTMenuBar;
    private javax.swing.JPanel DatabasePanel;
    private javax.swing.JLabel ManualLabel;
    private javax.swing.JLabel ManualLabel1;
    private javax.swing.JPanel ManualPanel;
    private javax.swing.JPanel WelcomePanel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeveloper;
    private javax.swing.JButton btnFilm;
    private javax.swing.JButton btnManualStart;
    private javax.swing.JButton btnNextStep;
    private javax.swing.JButton btnStartAgain;
    private javax.swing.JButton btnStartDb;
    private javax.swing.JTextArea detailTextArea;
    private javax.swing.JComboBox devMin;
    private javax.swing.JComboBox devSec;
    private javax.swing.JComboBox developerComboBox;
    private javax.swing.JLabel developerLabel;
    private javax.swing.JLabel developerLbl;
    private javax.swing.JComboBox filmComboBox;
    private javax.swing.JLabel filmLbl;
    private javax.swing.JPanel finalPanel;
    private javax.swing.JComboBox fixMin;
    private javax.swing.JComboBox fixSec;
    private javax.swing.JLabel fixerLabel;
    private javax.swing.JLabel hypoLabel;
    private javax.swing.JComboBox hypoMin;
    private javax.swing.JComboBox hypoSec;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblFinalTitle;
    private javax.swing.JLabel lblMinutes;
    private javax.swing.JLabel lblProcessIndicator;
    private javax.swing.JLabel lblSeconds;
    private javax.swing.JLabel prewashLabel;
    private javax.swing.JComboBox prewashMin;
    private javax.swing.JComboBox prewashSec;
    private javax.swing.JLabel stopLabel;
    private javax.swing.JComboBox stopMin;
    private javax.swing.JComboBox stopSec;
    private javax.swing.JPanel timerPanel;
    private javax.swing.JLabel washLabel;
    private javax.swing.JComboBox washMin;
    private javax.swing.JComboBox washSec;
    private javax.swing.JButton welcomeDatabaseBtn;
    private javax.swing.JButton welcomeManualBtn;
    private javax.swing.JComboBox wetMin;
    private javax.swing.JComboBox wetSec;
    private javax.swing.JLabel wettingLabel;
    // End of variables declaration//GEN-END:variables
}
