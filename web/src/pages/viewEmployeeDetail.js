import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
//Global variable to track the very first employee Id retrieved and the first page.

class ViewEmployeeDetail extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayEmpDetails' ], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.displayEmpDetails);
        this.header = new Header(this.dataStore);
    }

 /**
     * Once the client is loaded, get the employee details.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const employeeId = urlParams.get('id');
        const employeeDetail = await this.client.getEmployee(employeeId);
        this.dataStore.set('employeeDetail', employeeDetail);
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


 /**
     * Display employee details  on the page.
     */
    displayEmpDetails() {
        const employeeDetail = this.dataStore.get('employeeDetail');

        if (!employeeDetail) {
            return;
        }

        if (employeeDetail.firstName){
            document.getElementById('fname').value = employeeDetail.firstName;
        }
        if (employeeDetail.lastName){
            document.getElementById('lname').value = employeeDetail.lastName;
        }
        if (employeeDetail.jobTitle){
            document.getElementById('jobtitle').value = employeeDetail.jobTitle;
        }
        if (employeeDetail.email){
            document.getElementById('email').value = employeeDetail.email;
        }
        if (employeeDetail.phoneNumber){
            document.getElementById('phone').value = employeeDetail.phoneNumber;
        }
        if (employeeDetail.deptId){
            document.getElementById('dept').value = employeeDetail.deptId;
        }
        if (employeeDetail.dateOfBirth){
            document.getElementById('dob').value = employeeDetail.dateOfBirth;
        }
        if (employeeDetail.hireDate){
            document.getElementById('hireDate').value = employeeDetail.hireDate;
        }

 }


}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewEmployeeDetail = new ViewEmployeeDetail();
    await viewEmployeeDetail.mount();
};

window.addEventListener('DOMContentLoaded', main);