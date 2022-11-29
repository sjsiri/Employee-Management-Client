import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create Employee page of the website.
 */
class CreateEmployee extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'loadDeptDropDown', 'submit', 'redirectToViewEmployee'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
            this.loadDeptDropDown();
        }


    /**
     * Add the header to the page and load the EmployeeMgmtClient.
     */
    async mount() {
        document.getElementById('save-employee').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();
    }

    /**
    * Add loadDeptDropDown in order to select department
    */

    async loadDeptDropDown() {
           //Get all depts API
           const departments = await this.client.getAllDepartments();
           console.log(departments);
           const deptsDropDown = document.getElementById('depts');

           for (let key of departments) {
              let option = document.createElement("option");
              option.setAttribute('value', key.deptId);
              option.setAttribute('innerHTML', key.deptName);
              let optionText = document.createTextNode(key.deptName);
              option.appendChild(optionText);
              deptsDropDown.appendChild(option);
            }
        }

    /**
     * Method to run when the create Employee submit button is pressed. Call the EmployeeMgmtClient to create the
     * Employee.
     */
    async submit() {
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const jobTitle = document.getElementById('jobTitle').value;
        const email = document.getElementById('email').value;
        const phoneNumber = document.getElementById('phoneNumber').value;
        const dept = document.getElementById('depts');
        const deptId = document.getElementById('depts').value;
        const deptName = dept.options[dept.selectedIndex].innerHTML;
        const hireDate = document.getElementById('hireDate').value;
        const dateOfBirth = document.getElementById('dateOfBirth').value;
        const employeeStatus = "Active";
        const employee = await this.client.createEmployee(firstName, lastName,
        jobTitle, email, phoneNumber, deptId, deptName, hireDate, dateOfBirth, employeeStatus);
        this.dataStore.set('employee', employee);
        this.redirectToViewEmployee();
    }

    /**
     * When the Employee is updated in the datastore, redirect to the view Employee page.
     */
    redirectToViewEmployee() {
        const employee = this.dataStore.get('employee');
        if (employee) {
            window.location.href = `/index.html`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createEmployee = new CreateEmployee();
    createEmployee.mount();
};

window.addEventListener('DOMContentLoaded', main);
