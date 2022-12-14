import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view department page of the website.
 */

class ViewEmployees extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount',  'displayEmployeesOnPage', 'loadDeptDropDown' , 'generateTable',  'next', 'previous', 'deptChange' ], this);
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
        document.getElementById('previous').style.background='#454545';
        //Get all employees API
        const employees = await this.client.getAllEmployees("A", true);
        this.dataStore.set('employees', employees);
        this.dataStore.set('veryFirstEmpId', employees[0].lastNameEmployeeId);
        this.dataStore.set('firstEmpId', employees[0].lastNameEmployeeId);
        await this.loadDeptDropDown();
    }


    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
        document.getElementById('next').addEventListener('click', this.next);
        document.getElementById('previous').addEventListener('click', this.previous);
        document.getElementById('depts').addEventListener('change', this.deptChange);

        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();
    }

   async loadDeptDropDown() {
       //Get all depts API
       document.getElementById('dept_loading').innerText = "(Loading department list...)";
       const departments = await this.client.getAllDepartments();
       departments.push(
            {
               "deptId": "ALL",
               "deptName": "ALL",
               "deptStatus": "Active"
            }
       );

       this.dataStore.set('departments', departments);
       const deptsDropDown = document.getElementById('depts');

       for (let key of departments) {
          let option = document.createElement("option");
          option.setAttribute('value', key.deptId);
          option.setAttribute('innerHTML', key.deptName);
          let optionText = document.createTextNode(key.deptName);
          option.appendChild(optionText);
          deptsDropDown.appendChild(option);
        }
        document.getElementById('dept_loading').innerText = "";
        deptsDropDown.value = "ALL";

    }

    async generateTable(table, data) {

      if (data.length != 0) {
          for (let element of data) {
            let row = table.insertRow();

            row.addEventListener('click', async evt => {
                        window.location.href = `/view_employee.html?id=${element.employeeId}`;
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
      }
    }

 /**
     * When the employees are updated in the datastore, update the list of employees on the page.
     */
    async displayEmployeesOnPage() {
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

        if (employees.length === 0) {
            document.getElementById('employees').innerText = "(No employees found...)";
        }
 }

     async next() {
         const employees = this.dataStore.get('employees');

         const dept = document.getElementById('depts');
         const deptId = document.getElementById('depts').value;
         const deptName = dept.options[dept.selectedIndex].innerHTML;

         if (deptId == 'ALL'){
            const employeesNext = await this.client.getAllEmployees(employees[19].lastNameEmployeeId, true);
            if (employeesNext.length !=0){
                document.getElementById('previous').disabled = false;
                document.getElementById('previous').style.background='#ff7f50';
                this.dataStore.set('employees', employeesNext);
            }
             if (employeesNext.length < 20) {
                document.getElementById('next').disabled = true;
                document.getElementById('next').style.background='#454545';
            }
         }
         else {
            const employeesInDept = await this.client.getAllEmployeesByDept(employees[19].lastNameEmployeeId, true, deptId);
            if (employeesInDept.length !=0){
               document.getElementById('previous').disabled = false;
               document.getElementById('previous').style.background='#ff7f50';
               this.dataStore.set('employees', employeesInDept);
            }
             if (employeesInDept.length < 20)  {
               document.getElementById('next').disabled = true;
               document.getElementById('next').style.background='#454545';
            }
         }
         this.dataStore.set('firstEmpId', employees[0].lastNameEmployeeId);
     }


     async previous() {
         const employees = this.dataStore.get('employees');

         const dept = document.getElementById('depts');
         const deptId = document.getElementById('depts').value;
         const deptName = dept.options[dept.selectedIndex].innerHTML;

         if (deptId == 'ALL'){
            const employeesPrev = await this.client.getAllEmployees(employees[0].lastNameEmployeeId, false);
            this.dataStore.set('employees', employeesPrev);
            if (employeesPrev.length != 0 && employeesPrev[0].lastNameEmployeeId ==  this.dataStore.get('veryFirstEmpId')){
                document.getElementById('previous').disabled = true;
                document.getElementById('previous').style.background='#454545';
            }

         } else {
            const employeesInDept = await this.client.getAllEmployeesByDept(employees[0].lastNameEmployeeId, false, deptId);
            this.dataStore.set('employees', employeesInDept);
            if (employeesInDept.length != 0 && employeesInDept[0].lastNameEmployeeId ==  this.dataStore.get('veryFirstEmpIdOfDept')){
                document.getElementById('previous').disabled = true;
                document.getElementById('previous').style.background='#454545';
            }
         }
         document.getElementById('next').disabled = false;
         document.getElementById('next').style.background='#ff7f50';
         this.dataStore.set('firstEmpId', employees[0].lastNameEmployeeId);
     }

      async deptChange() {
         //Disable prev button on initial load
         document.getElementById('previous').disabled = true;
         document.getElementById('previous').style.background='#454545';

         document.getElementById('next').disabled = false;
         document.getElementById('next').style.background='#ff7f50';

         const dept = document.getElementById('depts');

         const deptId = document.getElementById('depts').value;
         const deptName = dept.options[dept.selectedIndex].innerHTML;

         if (deptId == 'ALL'){
            const employees = await this.client.getAllEmployees(0, true, deptId);
            this.dataStore.set('employees', employees);
            this.dataStore.set('veryFirstEmpIdOfDept', employees[0].lastNameEmployeeId);
            if (employees.length < 20) {
                document.getElementById('next').disabled = true;
                document.getElementById('next').style.background='#454545';
            }
         } else {
            const employeesInDept = await this.client.getAllEmployeesByDept(0, true, deptId);
            this.dataStore.set('employees', employeesInDept);
            this.dataStore.set('veryFirstEmpIdOfDept', employeesInDept[0].lastNameEmployeeId);
             if (employeesInDept.length < 20) {
                 document.getElementById('next').disabled = true;
                 document.getElementById('next').style.background='#454545';
             }
         }
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