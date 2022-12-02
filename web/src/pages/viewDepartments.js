import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view department page of the website.
 */

class ViewDepartments extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount',  'displayEmployeesOnPage','generateTable',], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayEmployeesOnPage);
        this.header = new Header(this.dataStore);
    }

 /**
     * Once the client is loaded, get the departments list.
     */
    async clientLoaded() {
        document.getElementById('departments').innerText = "(Loading department list...)";
        //Get all departments API
        const departments = await this.client.getAllDepartments();
        this.dataStore.set('departments', departments);

    }


    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {

        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();
    }


    async generateTable(table, data) {

      if (data.length != 0) {
          for (let element of data) {
            let row = table.insertRow();

            row.addEventListener('click', async evt => {
                        window.location.href = `/view_department.html?id=${element.deptId}`;
                      });


            let cell = row.insertCell();
            let text = document.createTextNode(element.deptId);
            cell.appendChild(text);


            cell = row.insertCell();
            text = document.createTextNode(element.deptName);
            cell.appendChild(text);


            cell = row.insertCell();
            text = document.createTextNode(element.deptStatus);
            cell.appendChild(text);
          }
      }
    }

 /**
     * When the departments are updated in the datastore, update the list of departments on the page.
     */
    async displayEmployeesOnPage() {
        const departments = this.dataStore.get('departments');

        if (!departments) {
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
        this.generateTable(table, departments);
        document.getElementById('departments').innerText = "";

        if (departments.length === 0) {
            document.getElementById('departments').innerText = "(No departments found...)";
        }
 }

}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewDepartments = new ViewDepartments();
    await viewDepartments.mount();
};


window.addEventListener('DOMContentLoaded', main);