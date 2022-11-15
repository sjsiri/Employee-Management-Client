import EmployeeMgmtClient from '../api/employeeMgmtClient';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor(dataStore = new DataStore()) {
        super();
        const methodsToBind = ['clientLoaded', 'loadData', 'addHeaderToPage', 'updateUsernameInHeader'];
        this.bindClassMethods(methodsToBind, this);
        this.dataStore = dataStore;
        this.dataStore.set('username', '');
        this.dataStore.addChangeListener(this.updateUsernameInHeader);
    }

    /**
     * Once the client has loaded successfully, get the identity of the current user.
     * @returns {Promise<void>}
     */
    async clientLoaded() {
        // TODO auth?
        //const identity = await this.client.getIdentity();
        //this.dataStore.set('username', identity.username);
        this.dataStore.set('username', 'Nashville Software School');
    }

    loadData() {
        this.client = new EmployeeMgmtClient({ onReady: this.clientLoaded });
    }

    /**
     * Add the header to the page.
     */
    addHeaderToPage() {
        document.getElementById('header').innerHTML = `
            <div class="site-title">
                <a class="header_home" href="index.html">Employee Management Client</a>
            </div>
            <div id="user">${this.dataStore.get('username')}</div>
        `;
    }

    /**
     * When the datastore has been updated, update the username in the header.
     */
    updateUsernameInHeader() {
        document.getElementById('user').innerText = this.dataStore.get('username');
    }
}
