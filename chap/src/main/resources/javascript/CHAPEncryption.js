function ChapEncryption(password, salt, challenge) {

    digest.init();
    digest.update(password);
    digest.update(salt);
    var passwordHash = digest.finalize();

    digest.init();
    digest.update(passwordHash);
    digest.update(challenge);
    var hash = digest.finalize();
    return hash.toString(CryptoJS.enc.Base64);
}