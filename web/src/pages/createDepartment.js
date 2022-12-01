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

    document.getElementById('save-department').className = 'disabled';
    const department = await this.client.createDepartment(payload);
    this.datastore.set('department', department);
    redirectToViewDepartment();
    }

    redirectToViewDepartment() {
        const department = this.dataStore.get('department');
        if (department.deptName) {
            window.location.href = `/departmentIndex.html`;
        }
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
