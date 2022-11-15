import EmployeeMgmtClient from '../api/employeeMgmtClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class ViewEmployees extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addEmployeesToPage', 'addEmployee'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addEmployeesToPage);
        this.header = new Header(this.dataStore);
        console.log("viewEmployees constructor");
    }

 /**
     * Once the client is loaded, get the employees list.
     */
    async clientLoaded() {
        //const urlParams = new URLSearchParams(window.location.search);
        //const playlistId = urlParams.get('id');
        //document.getElementById('playlist-name').innerText = "Loading Playlist ...";
        //const playlist = await this.client.getPlaylist(playlistId);
        //this.dataStore.set('playlist', playlist);
        document.getElementById('employees').innerText = "(Loading employee list...)";
        const employees = await this.client.getAllEmployees();
        this.dataStore.set('employees', employees);
        this.addEmployeesToPage();

    }

    /**
     * Add the header to the page and load the EmployeeMgmtClientClient.
     */
    mount() {
        document.getElementById('add-employee').addEventListener('click', this.addEmployee);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new EmployeeMgmtClient();
        this.clientLoaded();
    }

 /**
     * When the employees are updated in the datastore, update the list of employees on the page.
     */
    addEmployeesToPage() {
        const employees = this.dataStore.get('employees')
        alert(employees);
        if (employees == null) {
            return;
        }

        let employeeHtml = '';
        let employee;
        for (employee of employees) {
            employeeHtml += `
                <li class="employee">
                    <span class="department">${employee.deptId}</span>
                    <span class="firstname">${employee.firstName}</span>
                    <span class="lastname">${employee.lastName}</span>
                    <span class="email">${employee.email}</span>

                </li>
            `;
        }
        document.getElementById('employees').innerHTML = employeeHtml;
    }

 /**
     * Method to run when the add employee submit button is pressed. Call the EmployeeMgmtClient to add employee to the
     * list.
     */
     //TODO: Shilpa I think this may need to be moved to the add_employee page js because of our UI design
    async addEmployee() {
        const playlist = this.dataStore.get('playlist');
        if (playlist == null) {
            return;
        }

//        document.getElementById('add-song').innerText = 'Adding...';
//        const asin = document.getElementById('album-asin').value;
//        const trackNumber = document.getElementById('track-number').value;
//        const playlistId = playlist.id;

        const songList = await this.client.addEmployee(playlistId, asin, trackNumber);
        this.dataStore.set('songs', songList);

        document.getElementById('add-song').innerText = 'Add Song';
        document.getElementById("add-song-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewEmployees = new ViewEmployees();
    viewEmployees.mount();
};

window.addEventListener('DOMContentLoaded', main);