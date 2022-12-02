import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create Department page of the website.
 */
class CreateDepartment extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewDepartment'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the EmployeeMgmtClient.
     */
    async mount() {
        document.getElementById('save-department').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
    }

    /**
     * Method to run when the create Department submit button is pressed.
     * Calls the EmployeeMgmtClient to create the department.
     */
    async submit() {
    const submitButton = document.getElementById('save-department');
    const nameRegex = new RegExp('[^a-zA-Z\\s-\'.]');

    const deptId = document.getElementById('deptId').value;
    const deptName = document.getElementById('deptName').value;
    const deptStatus = document.getElementById('status').value;

    if (!deptId || !deptName || !deptStatus) {
        alert("Please fill in all required fields");
        return;
    }

    if (nameRegex.test(deptName)) {
        alert("The department name you entered is invalid");
        return;
    }

    let payload = {deptId: deptId, deptName:deptName, deptStatus: deptStatus}

    submitButton.className = 'disabled';
    submitButton.innerHTML = "Creating Department...";
    try {
        const response = await this.client.createDepartment(payload);
    } catch (error) {
        submitButton.style.background = '#F5881F';
        submitButton.disable = false;
        submitButton.innerHTML = "Create Department"
        alert(error.response.data.replace('{ "error_message": "', '').replace('" }', ''));
        return;
    }
    this.redirectToViewDepartment();
    }

    redirectToViewDepartment() {
        window.location.href = `/departmentIndex.html`;
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createDepartment = new CreateDepartment();
    createDepartment.mount();
};

window.addEventListener('DOMContentLoaded', main);
