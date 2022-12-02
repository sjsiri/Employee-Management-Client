import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view employee page of the website.
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
        document.getElementById('emp_loading').innerHTML = "(Loading employee details...)";

        const employeeDetail = await this.client.getEmployee(employeeId);
        this.dataStore.set('employeeDetail', employeeDetail);
        document.getElementById('update-employee').addEventListener('click', async evt => {
                            window.location.href = `/update_employee.html?id=${employeeId}`;
                          });

        document.getElementById('emp_loading').innerHTML = "";
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
            document.getElementById('fname').innerHTML = employeeDetail.firstName;
        }
        if (employeeDetail.lastName){
            document.getElementById('lname').innerHTML = employeeDetail.lastName;
        }
        if (employeeDetail.jobTitle){
            document.getElementById('jobtitle').innerHTML = employeeDetail.jobTitle;
        }
        if (employeeDetail.email){
            document.getElementById('email').innerHTML = employeeDetail.email;
        }
        if (employeeDetail.phoneNumber){
            document.getElementById('phone').innerHTML = employeeDetail.phoneNumber;
        }
        if (employeeDetail.deptId){
            document.getElementById('deptId').innerHTML = employeeDetail.deptId;
        }
        if (employeeDetail.deptName){
            document.getElementById('deptName').innerHTML = employeeDetail.deptName;
        }
        if (employeeDetail.dateOfBirth){
            document.getElementById('dob').innerHTML = employeeDetail.dateOfBirth;
        }
        if (employeeDetail.hireDate){
            document.getElementById('hireDate').innerHTML = employeeDetail.hireDate;
        }
        if (employeeDetail.employeeStatus){
            document.getElementById('employeeStatus').innerHTML = employeeDetail.employeeStatus;
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