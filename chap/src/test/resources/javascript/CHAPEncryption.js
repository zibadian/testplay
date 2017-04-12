load("crypto-js.js")

function PasswordEncryption(digest, password, salt) {
    print("starting password hash")
    digest.init();
    digest.update(password);
    digest.update(salt);
    print("finished password hash")
    return digest.finalize();
}

function ChapEncryption(digest, passwordHash, challenge) {
    print("starting encryption")
    digest.init();
    digest.update(passwordHash);
    digest.update(challenge);
    print("finished encryption")
    return digest.finalize();
}

print("Function created")

var passwordHash = PasswordEncryption(CryptoJS.algo.SHA3.create(), "test", "a");
print(passwordHash);
var response = ChapEncryption(CryptoJS.algo.SHA3.create(), passwordHash, "a");
print(response);
print((""+response).length);

print(CryptoJS.SHA3(""));
