function buildHeader() {
    fetch('/getCurrentUser').then(response => response.json()).then(user => {
        document.getElementById('pageHeader').innerHTML = '<b>' + user.email + '</b> ' + 'with roles:' + user.roles.map(role => role.name).join(' ')
    })
}

buildHeader()

function buildNav() {
    fetch('/getCurrentUser').then(response => response.json()).then(user => {
        document.getElementById('userRolesLink').innerHTML =
            user.roles.map(role => '<a class="nav-link' + (role.name === 'ADMIN' ? ' active" ' : '" ') + 'href="/' + role.name.toLowerCase() + '" role="tab">' + role.name.charAt(0).toUpperCase() + role.name.substring(1).toLowerCase() + '</a>').join('\n')
    })
}

buildNav()

function buildTable() {
    fetch('/getAllUsers').then(response => response.json()).then(users => {
        document.getElementById('tableBody').innerHTML = users.map(user => '<tr>' +
            `<td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>` +
            '<td>' + user.roles.map(role => role.name).join(' ') + '</td>' +
            '<td><button type="button" class="btn btn-primary" data-toggle="modal" id="editButton" data-target="#editModal">Edit</button></td>' +
            '<td><button type="submit" class="btn btn-danger" data-toggle="modal" id="deleteButton" data-target="#deleteModal">Delete</button></td>' +
            '</tr>').join('\n')
    })
}

buildTable()

function buildNewRoles(){
    fetch('/getAllRoles').then(response => response.json()).then(roles => {
        document.getElementById('newRoles').innerHTML = roles.map(role => `<option>${role.name}</option>`).join('\n')
    })
}

buildNewRoles()

const listenClick = (selector, handler) => {
    document.addEventListener('click', e => {
        console.log(e.target.closest(selector))
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

listenClick('#editButton', e => {
    let target = e.target.parentNode.parentNode
    document.getElementById('editId').value = target.children[0].innerHTML
    document.getElementById('editFirstName').value = target.children[1].innerHTML
    document.getElementById('editLastName').value = target.children[2].innerHTML
    document.getElementById('editAge').value = target.children[3].innerHTML
    document.getElementById('editEmail').value = target.children[4].innerHTML
    document.getElementById('editPassword').value = ''
    fetch('/getAllRoles').then(response => response.json()).then(roles => {
            document.getElementById('editRoles').innerHTML = roles.map(role => target.children[5].innerHTML.includes(role.name) ? `<option selected>${role.name}</option>` : `<option>${role.name}</option>`).join('\n')
        }
    )
})

listenClick('#deleteButton', e => {
    let target = e.target.parentNode.parentNode
    document.getElementById('deleteId').value = target.children[0].innerHTML
    document.getElementById('deleteFirstName').value = target.children[1].innerHTML
    document.getElementById('deleteLastName').value = target.children[2].innerHTML
    document.getElementById('deleteAge').value = target.children[3].innerHTML
    document.getElementById('deleteEmail').value = target.children[4].innerHTML
    fetch('/getAllRoles').then(response => response.json()).then(roles => {
            document.getElementById('deleteRoles').innerHTML = roles.map(role => target.children[5].innerHTML.includes(role.name) ? `<option selected>${role.name}</option>` : `<option>${role.name}</option>`).join('\n')
        }
    )
})



let roleArray = (options) => {
    let array = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            let role = {id:i, name: options[i].value}
            array.push(role)
        }
    }
    return array;
}



listenClick('#editFormButton', e => {
    e.preventDefault()
    let setRoles = roleArray(document.querySelector('#editRoles'))
    fetch('/editUser', {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
                id: document.getElementById('editId').value,
                firstName: document.getElementById('editFirstName').value,
                lastName: document.getElementById('editLastName').value,
                age: document.getElementById('editAge').value,
                email: document.getElementById('editEmail').value,
                password: document.getElementById('editPassword').value,
                roles: setRoles
            }
        )
    }).then(buildTable)
    $('#editModal').modal('hide')
})



listenClick('#deleteFormButton', e => {
    e.preventDefault()
    let setRoles = roleArray(document.querySelector('#deleteRoles'))
    fetch('/deleteUser', {
        method: 'DELETE',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            id: document.getElementById('deleteId').value,
            firstName: document.getElementById('deleteFirstName').value,
            lastName: document.getElementById('deleteLastName').value,
            age: document.getElementById('deleteAge').value,
            email: document.getElementById('deleteEmail').value,
            password: '',
            roles: setRoles
            }
        )
    }).then(buildTable)
    $('#deleteModal').modal('hide')
})

listenClick('#newUserButton', e => {
    e.preventDefault()
    let setRoles = roleArray(document.querySelector('#newRoles'))
    fetch('/newUser', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
                firstName: document.getElementById('newFirstName').value,
                lastName: document.getElementById('newLastName').value,
                age: document.getElementById('newAge').value,
                email: document.getElementById('newEmail').value,
                password: document.getElementById('newPassword').value,
                roles: setRoles
            }
        )
    }).then(buildTable)
    $('#myTab a[href="#nav-users"]').tab('show')
})
