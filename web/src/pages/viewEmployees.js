import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
//Global variable to track the very first employee Id retrieved and the first page.
var firstEmpId = '';
class ViewEmployees extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayEmployeesOnPage', 'generateTable', 'next', 'previous',  'addEmployee'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("viewEmployees constructor");
    }

 /**
     * Once the client is loaded, get the employees list.
     */
    async clientLoaded() {
        document.getElementById('employees').innerText = "(Loading employee list...)";
        //Disable prev button on initial load
        document.getElementById('previous').disabled = true;
        document.getElementById('previous').style.background='grey';
        //Get all employees API
        const employees = await this.client.getAllEmployees(0, true);
        this.dataStore.set('employees', employees);
        firstEmpId = employees[0].employeeId;
        console.log("This is my first employee Id from the table: ", firstEmpId )
        console.log("In ClientLoaded method: Employees values: ",  employees);
        this.displayEmployeesOnPage();

    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
        document.getElementById('add-employee').addEventListener('click', this.addEmployee);
        document.getElementById('next').addEventListener('click', this.next);
        document.getElementById('previous').addEventListener('click', this.previous);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();
    }

    generateTable(table, data) {

      for (let element of data) {
        let row = table.insertRow();

        let cell = row.insertCell();
        let text = document.createTextNode(element.deptName);
        cell.appendChild(text);

        cell = row.insertCell();
        text = document.createTextNode(element.firstName);
        cell.appendChild(text);

        cell = row.insertCell();
        text = document.createTextNode(element.lastName);
        cell.appendChild(text);

        cell = row.insertCell();
        // Create anchor element.
        var a = document.createElement('a');
        text = document.createTextNode(element.email);
        // Append the text node to anchor element.
        a.appendChild(text);
        a.title = element.email
        a.href = 'mailto:' + element.email;
        cell.appendChild(a);

      }
    }

 /**
     * When the employees are updated in the datastore, update the list of employees on the page.
     */
    displayEmployeesOnPage() {
        const employees = this.dataStore.get('employees');
        console.log("In DisplayEmployeesMethod: Employees values: ",  employees);
        
        if (!employees) {
            return;
        }
            let table = document.querySelector("table");
            let data = Object.keys(employees);

            var tableHeaderRowCount = 1;
            var rowCount = table.rows.length;
            for (var i = tableHeaderRowCount; i < rowCount; i++) {
                table.deleteRow(tableHeaderRowCount);
            }

            this.generateTable(table, employees);
            document.getElementById('employees').innerText = "";

            if (employees.length < 5){
                document.getElementById('next').disabled = true;
                document.getElementById('next').style.background='grey';
            }

            if (employees[0].employeeId == firstEmpId) {
                document.getElementById('previous').disabled = true;
                document.getElementById('previous').style.background='grey';
            }
 }

     async next() {
         const employees = this.dataStore.get('employees');
         const employeesNext = await this.client.getAllEmployees(employees[4].employeeId, true);
         this.dataStore.set('employees', employeesNext);
         this.displayEmployeesOnPage();

         document.getElementById('previous').disabled = false;
         document.getElementById('previous').style.background='#F5881F';

     }


     async previous() {
         const employees = this.dataStore.get('employees');
         const employeesPrev = await this.client.getAllEmployees(employees[0].employeeId, false);
         //Reversing since DynamoDB withScanIndexForward retrieves data in the reversed order
         employeesPrev.reverse();
         this.dataStore.set('employees', employeesPrev);
         this.displayEmployeesOnPage();
     }



 /**
     * Method to run when the add employee submit button is pressed. Call the EmployeeMgmtClient to add employee to the
     * list.
     */
     //TODO: Shilpa I think this may need to be moved to the add_employee page js because of our UI design
    async addEmployee() {
        const playlist = this.dataStore.get('playlist');
        if (playlist == null) {
            return;
        }

//        document.getElementById('add-song').innerText = 'Adding...';
//        const asin = document.getElementById('album-asin').value;
//        const trackNumber = document.getElementById('track-number').value;
//        const playlistId = playlist.id;

        const songList = await this.client.addEmployee(playlistId, asin, trackNumber);
        this.dataStore.set('songs', songList);

        document.getElementById('add-song').innerText = 'Add Song';
        document.getElementById("add-song-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewEmployees = new ViewEmployees();
    await viewEmployees.mount();
};

window.addEventListener('DOMContentLoaded', main);