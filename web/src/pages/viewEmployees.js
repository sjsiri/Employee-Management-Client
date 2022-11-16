import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
//Global variable to track the very first employee Id retrieved and the first page.

class ViewEmployees extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayEmployeesOnPage', 'generateTable', 'next', 'previous', 'getEmployee' ], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayEmployeesOnPage);
        this.header = new Header(this.dataStore);
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
        this.dataStore.set('firstEmpId', employees[0].employeeId);

    }

    async getEmployee(){
        //const employeeDetail = await this.client.getEmployee(employeeId);
        //this.dataStore.set('employeeDetail', employeeDetail);


    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
        document.getElementById('next').addEventListener('click', this.next);
        document.getElementById('previous').addEventListener('click', this.previous);

        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();
    }

    async generateTable(table, data) {

      for (let element of data) {
        let row = table.insertRow();

        row.addEventListener('click', async evt => {
                  console.log('The element that was clicked was ', element.employeeId);
                  //const singleEmployee = await this.client.getEmployee(element.employeeId);
                  //if (singleEmployee) {
                    window.location.href = `/view_employee.html?id=${element.employeeId}`;
                  //}
                  });


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

      document.querySelectorAll('td').forEach(cell => {
//          cell.addEventListener('click', evt => {
//          console.log('The element that was clicked was ', evt.target);
//          });
      });
    }

 /**
     * When the employees are updated in the datastore, update the list of employees on the page.
     */
    displayEmployeesOnPage() {
        const employees = this.dataStore.get('employees');


        if (!employees) {
            return;
        }
            let table = document.querySelector("table");

            //Flush the table first
            var tableHeaderRowCount = 1;
            var rowCount = table.rows.length;
            for (var i = tableHeaderRowCount; i < rowCount; i++) {
                table.deleteRow(tableHeaderRowCount);
            }
            //Generate table data with the new set of employees
            this.generateTable(table, employees);
            document.getElementById('employees').innerText = "";

            if (employees.length < 20){
                document.getElementById('next').disabled = true;
                document.getElementById('next').style.background='grey';
            } else {
                document.getElementById('next').disabled = false;
                document.getElementById('next').style.background='#F5881F';
            }

            if (employees[0].employeeId ==  this.dataStore.get('firstEmpId')) {
                document.getElementById('previous').disabled = true;
                document.getElementById('previous').style.background='grey';
            }
 }

     async next() {
         const employees = this.dataStore.get('employees');
         const employeesNext = await this.client.getAllEmployees(employees[19].employeeId, true);
         this.dataStore.set('employees', employeesNext);

         document.getElementById('previous').disabled = false;
         document.getElementById('previous').style.background='#F5881F';

     }


     async previous() {
         const employees = this.dataStore.get('employees');
         const employeesPrev = await this.client.getAllEmployees(employees[0].employeeId, false);
         this.dataStore.set('employees', employeesPrev);
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