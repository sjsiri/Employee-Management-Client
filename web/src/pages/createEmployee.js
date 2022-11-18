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
        this.bindClassMethods(['mount', 'submit', 'redirectToViewEmployee'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicEmployeeClient.
     */
    mount() {
        document.getElementById('save-employee').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
    }

    /**
     * Method to run when the create Employee submit button is pressed. Call the MusicEmployeeService to create the
     * Employee.
     */
    async submit() {
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const jobTitle = document.getElementById('jobTitle').value;
        const email = document.getElementById('email').value;
        const phoneNumber = document.getElementById('phoneNumber').value;
        const deptId = document.getElementById('deptId').value;
        const deptName = document.getElementById('deptName').value;
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
