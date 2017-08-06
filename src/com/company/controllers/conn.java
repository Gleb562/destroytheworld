package com.company.controllers;

import com.company.objects.Client;
import com.company.objects.Contract;
import com.company.objects.Person;
import com.company.objects.User;
import com.company.start.db;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class conn {

    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnDeleteClient;
    @FXML
    private Button btnEditClient;
    @FXML
    private Button btnEditContract;
    @FXML
    private Button btnDeleteContract;
    @FXML
    private TableColumn<User,Integer> clmnId;
    @FXML
    private TableColumn<User, Integer> clmnPrice;
    @FXML
    private TableColumn<User, String> clmnPname;
    @FXML
    private TableView<User> table;
    @FXML
    private TextField tfieldPrice;
    @FXML
    private TextField tfieldPname;

    @FXML
    private TextField txtFieldClientName;
    @FXML
    private TextField txtFieldClientPhone;
    @FXML
    private TextField txtFieldClientEmail;
    @FXML
    private TableView<Client> tableClients;
    @FXML
    private TableColumn<Client, Integer> clmnClientId;
    @FXML
    private TableColumn<Client, String> clmnClientPhone;
    @FXML
    private TableColumn<Client, String> clmnClientName;
    @FXML
    private TableColumn<Client, String> clmnClientEmail;

    @FXML
    private TextField txtFieldContractName;
    @FXML
    private TextField txtFieldContractProfit;
    @FXML
    private TextField txtFieldContractDate;
    @FXML
    private TableView<Contract> tableContracts;
    @FXML
    private TableColumn<Contract, Integer> clmnContractId;
    @FXML
    private TableColumn<Contract, String> clmnContractDate;
    @FXML
    private TableColumn<Contract, String> clmnContractProfit;
    @FXML
    private TableColumn<Contract, String> clmnContractName;

    @FXML
    private TableView<Person> tableUsers;
    @FXML
    private TableColumn<Person, Integer> clmnUsersId;
    @FXML
    private TableColumn<Person, String> clmnUsersUsername;
    @FXML
    private TableColumn<Person, String> clmnUsersPassword;
    @FXML
    private TableColumn<Person, Integer> clmnUsersAccessLvl;

    @FXML
    private Tab tabAdmin;


    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;
    private User user;
    private Client client;
    private Contract contract;
    private Person person;

    private Parent fxmlEdit;
    private Parent fxmlClientEdit;
    private Parent fxmlContractEdit;
    private Parent login1;
    private Parent fxmlUsertEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private FXMLLoader fxmlClientLoader = new FXMLLoader();
    private FXMLLoader fxmlContractLoader = new FXMLLoader();
    private FXMLLoader loginLoader = new FXMLLoader();
    private FXMLLoader fxmlUserLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private EditClientDialogController editClientDialogController;
    private EditContractDialogController editContractDialogController;
    private LogInController loginController;
    private EditUserDialogController editUserDialogController;
    private Stage editDialogStage;

    private ObservableList<User> usersData = FXCollections.observableArrayList();
    private ObservableList<Client> ClientsData = FXCollections.observableArrayList();
    private ObservableList<Contract> ContractsData = FXCollections.observableArrayList();
    private ObservableList<Person> PersonData = FXCollections.observableArrayList();
    db DB = new db();

    static int currentAccessLvl;

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        try {

            fxmlLoader.setLocation(getClass().getResource("../fxml/EditDialog.fxml"));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();
            fxmlClientLoader.setLocation(getClass().getResource("../fxml/EditClientsDialog.fxml"));
            fxmlClientEdit = fxmlClientLoader.load();
            editClientDialogController = fxmlClientLoader.getController();
            fxmlContractLoader.setLocation(getClass().getResource("../fxml/EditContractsDialog.fxml"));
            fxmlContractEdit = fxmlContractLoader.load();
            editContractDialogController = fxmlContractLoader.getController();
            fxmlUserLoader.setLocation(getClass().getResource("../fxml/EditUserDialog.fxml"));
            fxmlUsertEdit = fxmlUserLoader.load();
            editUserDialogController = fxmlUserLoader.getController();

            loginLoader.setLocation(getClass().getResource("../fxml/LogIn.fxml"));
            login1 = loginLoader.load();
            loginController = loginLoader.getController();

            loadTable();
            loadClientsTable();
            loadContractTable();
            loadUsersTable();
            sss1();
            CheckUser();
            /*Window parentWindow = DB.getWindow();
            showLoginDialog();*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // подготавливаем данные для таблицы
    // получаем их из базы данных
    private void initData() throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM prices");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  pname = resSet.getString("pname");
            int  price = resSet.getInt("price");

            usersData.add(new User(id, pname, price));
        }
    }

    private void initClientsData() throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM clients");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("client_name");
            String  phone = resSet.getString("phone");
            String  email = resSet.getString("email");

            ClientsData.add(new Client(id,email,phone,name));
        }
    }

    private void initContractsData() throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM contracts");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("contract_name");
            int  profit = resSet.getInt("profit");
            String  date = resSet.getString("date");

            ContractsData.add(new Contract(id,profit,date,name));
        }
    }

    private void initUsersData() throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM user");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            int  access_level = resSet.getInt("access_level");
            String  username = resSet.getString("username");
            String  password = resSet.getString("password");

            PersonData.add(new Person(id,username,password,access_level));
        }
    }

    /*private void initPersonData() throws SQLException {
        resSet = statmt.executeQuery("SELECT * FROM contracts");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("contract_name");
            int  profit = resSet.getInt("profit");
            String  date = resSet.getString("date");

            ContractsData.add(new Contract(id,profit,date,name));
        }
    }*/

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:db1.s3db");

        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        //statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
        //System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    /*public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        System.out.println("Таблица заполнена");
    }*/

    // -------- Вывод таблицы--------
    /*public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  phone = resSet.getString("phone");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }*/

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        statmt.execute("DROP TABLE users");
        statmt.close();
        resSet.close();
        conn.close();
        System.out.println("Соединения закрыты");
    }



    public void showLoginDialog(Window parentWindow) {


        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Welcome");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            if (login1.getScene() == null) {
                Scene scene = new Scene(login1);
                editDialogStage.setScene(scene);
            } else {
                editDialogStage.setScene(login1.getScene());
            }
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
            editDialogStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    editDialogStage.close();
                    DB.closeWindow();
                }
            });
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна
        editDialogStage = null;
    }

    private void showEditDialog(Window parentWindow) {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Edit price");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            if (fxmlEdit.getScene() == null) {
                Scene scene = new Scene(fxmlEdit);
                editDialogStage.setScene(scene);
            } else {
                editDialogStage.setScene(fxmlEdit.getScene());
            }
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна
        editDialogStage = null;
    }

    private void showClientEditDialog(Window parentWindow) {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Edit client");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            if (fxmlClientEdit.getScene() == null) {
                Scene scene = new Scene(fxmlClientEdit);
                editDialogStage.setScene(scene);
            } else {
                editDialogStage.setScene(fxmlClientEdit.getScene());
            }
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна
        editDialogStage = null;

    }

    private void showContractEditDialog(Window parentWindow) {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Edit contract");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            if (fxmlContractEdit.getScene() == null) {
                Scene scene = new Scene(fxmlContractEdit);
                editDialogStage.setScene(scene);
            } else {
                editDialogStage.setScene(fxmlContractEdit.getScene());
            }
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна
        editDialogStage = null;
    }


    private void showUserEditDialog(Window parentWindow) {

        if (editDialogStage==null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Edit user");
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            if (fxmlUsertEdit.getScene() == null) {
                Scene scene = new Scene(fxmlUsertEdit);
                editDialogStage.setScene(scene);
            } else {
                editDialogStage.setScene(fxmlUsertEdit.getScene());
            }
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(parentWindow);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна
        editDialogStage = null;
    }



    public void actionOk(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void loadTable() throws SQLException, ClassNotFoundException {
        CreateDB();
        table.getItems().clear();
        try {initData();}
        catch (SQLException e) {e.printStackTrace();}

        // устанавливаем тип и значение которое должно хранится в колонке
        clmnId.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        clmnPrice.setCellValueFactory(new PropertyValueFactory<User, Integer>("price"));
        clmnPname.setCellValueFactory(new PropertyValueFactory<User, String>("pname"));

        // заполняем таблицу данными
        table.setItems(usersData);
    }
    public void loadClientsTable() throws SQLException, ClassNotFoundException {
        CreateDB();
        tableClients.getItems().clear();
        try {initClientsData();}
        catch (SQLException e) {e.printStackTrace();}

        // устанавливаем тип и значение которое должно хранится в колонке
        clmnClientId.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id"));
        clmnClientPhone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
        clmnClientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        clmnClientEmail.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));

        // заполняем таблицу данными
        tableClients.setItems(ClientsData);
    }

    public void loadContractTable() throws SQLException, ClassNotFoundException {
        CreateDB();
        tableContracts.getItems().clear();
        try {initContractsData();}
        catch (SQLException e) {e.printStackTrace();}

        // устанавливаем тип и значение которое должно хранится в колонке
        clmnContractId.setCellValueFactory(new PropertyValueFactory<Contract, Integer>("id"));
        clmnContractDate.setCellValueFactory(new PropertyValueFactory<Contract, String>("date"));
        clmnContractName.setCellValueFactory(new PropertyValueFactory<Contract, String>("clientName"));
        clmnContractProfit.setCellValueFactory(new PropertyValueFactory<Contract, String>("profit"));

        // заполняем таблицу данными
        tableContracts.setItems(ContractsData);
    }

    public void loadUsersTable() throws SQLException, ClassNotFoundException {
        CreateDB();
        tableUsers.getItems().clear();
        try {initUsersData();}
        catch (SQLException e) {e.printStackTrace();}

        // устанавливаем тип и значение которое должно хранится в колонке
        clmnUsersId.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
        clmnUsersUsername.setCellValueFactory(new PropertyValueFactory<Person, String>("username"));
        clmnUsersPassword.setCellValueFactory(new PropertyValueFactory<Person, String>("password"));
        clmnUsersAccessLvl.setCellValueFactory(new PropertyValueFactory<Person, Integer>("accessLevel"));

        // заполняем таблицу данными
        tableUsers.setItems(PersonData);
    }

    public void actionAdd(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Integer price1 = Integer.parseInt(tfieldPrice.getText());
        String pname1 = "\"" +tfieldPname.getText() + "\"";
        statmt.execute("INSERT INTO 'prices' ('pname', 'price') VALUES (" + pname1 +"," + price1 + "); ");
        loadTable();

    }


    public void deleteAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        User selectedPerson = (User) table.getSelectionModel().getSelectedItem();
        this.user = selectedPerson;
        int i = user.getId();
        statmt.execute("DELETE FROM prices WHERE id =" +  "\"" + i +  "\"");
        loadTable();
    }


    public void actionEdit(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object source = actionEvent.getSource();
        Button clickedButton = (Button) source;
        User selectedPerson = (User) table.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editDialogController.setUser(selectedPerson);
        showEditDialog(parentWindow);
        loadTable();
    }


    public void actionCancel(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionAddClients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String clientName ="\"" +  txtFieldClientName.getText() + "\"";
        String phone = "\"" +txtFieldClientPhone.getText() + "\"";
        String email = "\"" +txtFieldClientEmail.getText() + "\"";
        statmt.execute("INSERT INTO 'clients' ('client_name', 'phone', 'email') VALUES (" + clientName +"," + phone + "," + email + "); ");
        loadClientsTable();
    }

    public void artionDeleteClients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Client selectedPerson = (Client) tableClients.getSelectionModel().getSelectedItem();
        this.client = selectedPerson;
        int i = client.getId();
        statmt.execute("DELETE FROM clients WHERE id =" +  "\"" + i +  "\"");
        loadClientsTable();
    }

    public void actionEditClients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object source = actionEvent.getSource();
        Button clickedButton = (Button) source;
        Client selectedPerson = (Client) tableClients.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editClientDialogController.setClient(selectedPerson);
        showClientEditDialog(parentWindow);
        loadClientsTable();
    }

    public void actionAddContract(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String contractName ="\"" +  txtFieldContractName.getText() + "\"";
        String profit = "\"" + txtFieldContractProfit.getText() + "\"";
        String date = "\"" +txtFieldContractDate.getText() + "\"";
        statmt.execute("INSERT INTO 'contracts' ('contract_name', 'date', 'profit') VALUES (" + contractName +"," + date + "," + profit + "); ");
        loadContractTable();
    }

    public void actionDeleteContract(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Contract selectedPerson = (Contract) tableContracts.getSelectionModel().getSelectedItem();
        this.contract = selectedPerson;
        int i = contract.getId();
        statmt.execute("DELETE FROM contracts WHERE id =" +  "\"" + i +  "\"");
        loadContractTable();
    }

    public void arctionEditContract(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Object source = actionEvent.getSource();
        Button clickedButton = (Button) source;
        Contract selectedPerson = (Contract) tableContracts.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editContractDialogController.setContract(selectedPerson);
        showContractEditDialog(parentWindow);
        loadContractTable();
    }

    public void sss1() {
        Window parentWindow = DB.getWindow();
        showLoginDialog(parentWindow);
    }

    public void actionEditUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Person selectedPerson = (Person) tableUsers.getSelectionModel().getSelectedItem();
        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();
        editUserDialogController.setContract(selectedPerson);
        showUserEditDialog(parentWindow);
        loadUsersTable();
    }

    public void actionChangeUser(ActionEvent actionEvent) throws IOException {
                sss1();
                CheckUser();
    }

    public void CheckUser()
    {
        if(currentAccessLvl<1){
            btnDelete.setDisable(true);
            btnDeleteClient.setDisable(true);
            btnEdit.setDisable(true);
            btnEditClient.setDisable(true);
            btnDeleteContract.setDisable(true);
            btnEditContract.setDisable(true);
            tabAdmin.setDisable(true);
        }
        else {
            btnDelete.setDisable(false);
            btnDeleteClient.setDisable(false);
            btnEdit.setDisable(false);
            btnEditClient.setDisable(false);
            btnDeleteContract.setDisable(false);
            btnEditContract.setDisable(false);
            tabAdmin.setDisable(false);
        }
    }
}

