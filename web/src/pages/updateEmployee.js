import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view department page of the website.
 */
//Global variable to track the very first employee Id retrieved and the first page.

class ViewEmployeeDetail extends BindingClass {

    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayEmpDetails', 'update', 'loadDeptDropDown', 'redirectToViewEmployee'], this);
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
        await this.loadDeptDropDown();
        this.displayEmpDetails();
    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    async mount() {
        document.getElementById('save-employee').addEventListener('click', this.update);
        document.getElementById('depts').addEventListener('change', this.deptChange);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        await this.clientLoaded();

    }

deptChange() {
    const dept = document.getElementById('depts');
    const deptId = document.getElementById('depts').value;
    const deptName = dept.options[dept.selectedIndex].innerHTML;
    document.getElementById('deptId').innerHTML = deptId;
}

async loadDeptDropDown() {
       //Get all depts API
       const departments = await this.client.getAllDepartments();
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
     * Display employee details  on the page.
     */
    displayEmpDetails() {
             const employeeDetail = this.dataStore.get('employeeDetail');

             if (!employeeDetail) {
                 return;
             }
             if (employeeDetail.firstName){
                 document.getElementById('firstName').value = employeeDetail.firstName;
             }
             if (employeeDetail.lastName){
                 document.getElementById('lastName').value = employeeDetail.lastName;
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
             if (employeeDetail.dateOfBirth){
                 document.getElementById('dateOfBirth').value = employeeDetail.dateOfBirth;
             }
             if (employeeDetail.hireDate){
                 document.getElementById('hireDate').value = employeeDetail.hireDate;
             }
             if (employeeDetail.deptName){
                 const dept = document.getElementById('depts');

                 document.getElementById('depts').value = employeeDetail.deptId;
             }
             if (employeeDetail.deptId){
                 document.getElementById('deptId').innerHTML = employeeDetail.deptId;
             }
         
    }

    async update() {

        const nameRegex = new RegExp('[^a-zA-Z\\s-\'.]');
        const emailRegex = new RegExp('^[a-zA-Z0-9_!#$%&\'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$');
        const phoneRegex = new RegExp('\\D');
        const dept = document.getElementById('depts');
        const employeeId = this.dataStore.get('employeeId');
        const firstName = document.getElementById('firstName').value;
        const lastName = document.getElementById('lastName').value;
        const jobTitle = document.getElementById('jobtitle').value;
        const email = document.getElementById('email').value;
        const deptId = document.getElementById('depts').value;
        const deptName = dept.options[dept.selectedIndex].innerHTML;
        const hireDate = document.getElementById('hireDate').value;
        const phoneNumber = document.getElementById('phone').value;
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

        let payload = {employeeId: employeeId, firstName: firstName, lastName: lastName, email: email, dateOfBirth: dateOfBirth, employeeStatus: employeeStatus}

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
        const employee = await this.client.updateEmployee(payload);
        this.dataStore.set('employee', employee);
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