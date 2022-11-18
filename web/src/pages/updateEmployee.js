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
        this.bindClassMethods(['clientLoaded', 'mount', 'displayEmpDetails', 'update', 'redirectToViewEmployee'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

 /**
     * Once the client is loaded, get the employee details.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const employeeId = urlParams.get('id');
        this.dataStore.set('employeeId', employeeId);
        const employeeDetail = await this.client.getEmployee(employeeId);
        this.dataStore.set('employeeDetail', employeeDetail);
        this.displayEmpDetails();
    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
        document.getElementById('save-employee').addEventListener('click', this.update);
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
                 document.getElementById('deptId').value = employeeDetail.deptId;
             }
             if (employeeDetail.deptName){
                 document.getElementById('deptName').value = employeeDetail.deptName;
             }
             if (employeeDetail.dateOfBirth){
                 document.getElementById('dob').value = employeeDetail.dateOfBirth;
             }
             if (employeeDetail.hireDate){
                 document.getElementById('hireDate').value = employeeDetail.hireDate;
             }
             if (employeeDetail.employeeStatus){
                 document.getElementById('employeeStatus').value = employeeDetail.employeeStatus;
             }
    }

    async update() {
        const employeeId = this.dataStore.get('employeeId');
        const firstName = document.getElementById('fname').value;
        const lastName = document.getElementById('lname').value;
        const jobTitle = document.getElementById('jobtitle').value;
        const email = document.getElementById('email').value;
        const deptId = document.getElementById('deptId').value;
        const deptName = document.getElementById('deptName').value;
        const hireDate = document.getElementById('hireDate').value;
        const phoneNumber = document.getElementById('phone').value;
        const dateOfBirth = document.getElementById('dob').value;
        const employeeStatus = document.getElementById('employeeStatus').value;
        document.getElementById('save-employee').innerHTML = 'Saving Employee...';

        const employee = {
            employeeId,
            firstName,
            lastName,
            jobTitle,
            email,
            deptId,
            deptName,
            hireDate,
            dateOfBirth,
            employeeStatus};

        const employeeUpdated = await this.client.updateEmployee(employee);
        this.dataStore.set('employee', employeeUpdated);
        this.redirectToViewEmployee();
    }

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
    const updateEmployee = new ViewEmployeeDetail();
    await updateEmployee.mount();
};

window.addEventListener('DOMContentLoaded', main);