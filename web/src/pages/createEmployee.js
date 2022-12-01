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
        const nameRegex = new RegExp('[^a-zA-Z\\s-\'.]');
        const emailRegex = new RegExp('^[a-zA-Z0-9_!#$%&\'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$');
        const phoneRegex = new RegExp('\\D');

        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const jobTitle = document.getElementById('jobTitle').value;
        const email = document.getElementById('email').value;
        const phoneNumber = document.getElementById('phoneNumber').value;
        const dept = document.getElementById('depts');
        const deptId = document.getElementById('depts').value;
        const deptName = dept.options[dept.selectedIndex].innerHTML;
        const hireDate = document.getElementById('hireDate').value;
        console.log(hireDate);
        const dateOfBirth = document.getElementById('dateOfBirth').value;
        const employeeStatus = document.getElementById('employeeStatus').value;

        if (!firstName || !lastName || !email || !dateOfBirth || !employeeStatus) {
            alert("Please fill in all required fields");
            return;
        }
        if (nameRegex.test(firstName)) {
            alert("The first name you entered has invalid characters");
            return;
        }
        if (nameRegex.test(lastName)) {
            alert ("The last name you entered has invalid characters");
            return;
        }
        if (!emailRegex.test(email)) {
            alert("The email you entered is invalid");
            return;
        }
        if ((dateOfBirth.substring(0,4) < 1900) || (dateOfBirth.substring(0,4) > 2100) || (dateOfBirth.length != 10)) {
            alert("The date of birth you entered is invalid.");
            return;
        }

        let payload = {firstName: firstName, lastName: lastName, email: email, dateOfBirth: dateOfBirth, employeeStatus: employeeStatus}

        if (jobTitle) {
            if (nameRegex.test(jobTitle)) {
                alert("The job title you entered has invalid characters");
                return;
            }
            payload.jobTitle = jobTitle;
        }
        if (phoneNumber) {
            if (phoneRegex.test(phoneNumber)) {
                alert("The phone number you entered is invalid. Please use only digits");
                return;
            }
            payload.phoneNumber = phoneNumber;
        }
        if (deptId) {
            payload.deptId = deptId;
        }
        if (deptName) {
            payload.deptName = deptName;
        }
        if (hireDate) {
            if ((hireDate.substring(0,4) < 1900) || (hireDate.substring(0,4) > 2100) || (hireDate.length != 10)) {
                alert("The hire date you entered is invalid.");
                return;
            }
            payload.hireDate = hireDate;
        }
        document.getElementById('save-employee').disabled = true;
        document.getElementById('save-employee').innerHTML = 'Saving Employee...';
        document.getElementById('save-employee').style.background='grey';
        const employee = await this.client.createEmployee(payload);
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
