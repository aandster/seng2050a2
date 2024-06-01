function validateStartForm() {
    let providedUsername = document.getElementById("startGame_form_username").stringify()
    if (providedUsername == null || providedUsername === "") {
        alert("You must provide a username to start playing.");
        return false;
    } else {
        return true;
    }
}