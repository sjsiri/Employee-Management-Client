import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
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
     * Once the client is loaded, get the employees list.
     */
    async clientLoaded() {
        document.getElementById('departments').innerText = "(Loading employee list...)";
        //Disable prev button on initial load
//        document.getElementById('previous').disabled = true;
//        document.getElementById('previous').style.background='grey';
        //Get all employees API
        const departments = await this.client.getAllDepartments();
        this.dataStore.set('departments', departments);
//        this.dataStore.set('veryFirstDeptId', employees[0].employeeId);
//        this.dataStore.set('firstDeptId', employees[0].employeeId);
        //await this.loadDeptDropDown();
    }


    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
//        document.getElementById('next').addEventListener('click', this.next);
//        document.getElementById('previous').addEventListener('click', this.previous);

        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();
    }


    async generateTable(table, data) {

      if (data.length != 0) {
          for (let element of data) {
            let row = table.insertRow();

//            row.addEventListener('click', async evt => {
//                      console.log('The element that was clicked was ', element.employeeId);
//                        window.location.href = `/view_employee.html?id=${element.employeeId}`;
//                      });


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
     * When the employees are updated in the datastore, update the list of employees on the page.
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

//     async next() {
//         const employees = this.dataStore.get('employees');
//
//         const dept = document.getElementById('depts');
//         const deptId = document.getElementById('depts').value;
//         const deptName = dept.options[dept.selectedIndex].innerHTML;
//
//         if (deptId == 'ALL'){
//            const employeesNext = await this.client.getAllEmployees(employees[19].employeeId, true);
//            if (employeesNext.length !=0){
//                document.getElementById('previous').disabled = false;
//                document.getElementById('previous').style.background='#F5881F';
//                this.dataStore.set('employees', employeesNext);
//            }
//             if (employeesNext.length < 20) {
//                document.getElementById('next').disabled = true;
//                document.getElementById('next').style.background='grey';
//            }
//
//         } else {
//
//            const employeesInDept = await this.client.getAllEmployeesByDept(employees[19].employeeId, true, deptId);
//            if (employeesInDept.length !=0){
//               document.getElementById('previous').disabled = false;
//               document.getElementById('previous').style.background='#F5881F';
//               this.dataStore.set('employees', employeesInDept);
//            }
//             if (employeesInDept.length < 20)  {
//               document.getElementById('next').disabled = true;
//               document.getElementById('next').style.background='grey';
//            }
//
//         }
//
//         this.dataStore.set('firstEmpId', employees[0].employeeId);
//
//     }
//
//
//     async previous() {
//         const employees = this.dataStore.get('employees');
//
//         const dept = document.getElementById('depts');
//         const deptId = document.getElementById('depts').value;
//         const deptName = dept.options[dept.selectedIndex].innerHTML;
//
//         if (deptId == 'ALL'){
//            const employeesPrev = await this.client.getAllEmployees(employees[0].employeeId, false);
//            this.dataStore.set('employees', employeesPrev);
//            if (employeesPrev.length != 0 && employeesPrev[0].employeeId ==  this.dataStore.get('veryFirstEmpId')){
//                document.getElementById('previous').disabled = true;
//                document.getElementById('previous').style.background='grey';
//            }
//
//         } else {
//            const employeesInDept = await this.client.getAllEmployeesByDept(employees[0].employeeId, false, deptId);
//            this.dataStore.set('employees', employeesInDept);
//            if (employeesInDept.length != 0 && employeesInDept[0].employeeId ==  this.dataStore.get('veryFirstEmpIdOfDept')){
//                document.getElementById('previous').disabled = true;
//                document.getElementById('previous').style.background='grey';
//            }
//         }
//         document.getElementById('next').disabled = false;
//         document.getElementById('next').style.background='#F5881F';
//         this.dataStore.set('firstEmpId', employees[0].employeeId);
//     }
//
//      async deptChange() {
//         //Disable prev button on initial load
//         document.getElementById('previous').disabled = true;
//         document.getElementById('previous').style.background='grey';
//
//         document.getElementById('next').disabled = false;
//         document.getElementById('next').style.background='#F5881F';
//
//         const dept = document.getElementById('depts');
//
//         const deptId = document.getElementById('depts').value;
//         const deptName = dept.options[dept.selectedIndex].innerHTML;
//
//         console.log("Department ID is: ", deptId);
//         console.log("Department Name is: ", deptName);
//
//         if (deptId == 'ALL'){
//            const employees = await this.client.getAllEmployees(0, true, deptId);
//            this.dataStore.set('employees', employees);
//            this.dataStore.set('veryFirstEmpIdOfDept', employees[0].employeeId);
//            if (employees.length < 20) {
//                document.getElementById('next').disabled = true;
//                document.getElementById('next').style.background='grey';
//            }
//         } else {
//            const employeesInDept = await this.client.getAllEmployeesByDept(0, true, deptId);
//            this.dataStore.set('employees', employeesInDept);
//            this.dataStore.set('veryFirstEmpIdOfDept', employeesInDept[0].employeeId);
//             if (employeesInDept.length < 20) {
//                 document.getElementById('next').disabled = true;
//                 document.getElementById('next').style.background='grey';
//             }
//         }
//      }
//}
}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewDepartments = new ViewDepartments();
    await viewDepartments.mount();
};


window.addEventListener('DOMContentLoaded', main);