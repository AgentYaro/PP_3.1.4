function buildHeader() {
    fetch('/getCurrentUser').then(response => response.json()).then(user => {
    document.getElementById('pageHeader').innerHTML ='<b>' + user.email + '</b> ' + 'with roles:' + user.roles.map(role => ' ' + role.name).join(' ')
    })
}
buildHeader()

function buildInfo() {
    fetch('/getCurrentUser').then(response => response.json()).then(user => {
        document.getElementById('userInfo').innerHTML ='<td>'+user.id+'</td>'+
                                    '<td>'+user.firstName+'</td>'+
                                    '<td>'+user.lastName+'</td>'+
                                    '<td>'+user.age+'</td>'+
                                    '<td>'+user.email+'</td>'+'<td>'+
            user.roles.map(role =>role.name+' ').join(' ')+'</td>'
    })
}
buildInfo()

function buildNav() {
    fetch('/getCurrentUser').then(response => response.json()).then(user => {
        document.getElementById('userRolesLink').innerHTML =
            user.roles.map(role =>'<a class="nav-link' + (role.name==='USER'?' active" ':'" ') + 'href="/'  + role.name.toLowerCase()+'" role="tab">'+role.name.charAt(0).toUpperCase()+role.name.substring(1).toLowerCase()+'</a>' ).join('\n')
    })
}
buildNav()