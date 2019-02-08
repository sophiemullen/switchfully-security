**** Java Security Course ****

** Common attacks

Examples:
* Query using a filter that is e.g. 1=1 -> always true
* Being able to input html tags or even a stylesheet in a text editor/box.
**Table: display none in a stylesheet for a table = table never shows up
* Guessable ids, e.g. 1, 2, 3
* Returning more info than the user should see in the UI -> can see it in response even if not on screen
* Storing user info in cookies -> can be picked up from other websites. Should be stored in local storage?

** OWASP
* Makes a list of the 10 most used types of attacks and how they affect the website

** Types of attack
* Brute force attack
* Credential Recycling - reusing data obtained from earlier hacks
* Dictionary attack - loop over dictionary or hashed version of words and try every result
* Rainbow table - mapping of plaintext to hashes and tries them

** Salt & peppering passwords
* Salt
** password + random string
** Generate a string for each user, concantenate this to password before generating hash
** Always make a difference salt
** Why? Users with the same password will never have the same hash

* Pepper
** Fixed random string per application - add string to password


* Man in the middle
* Can either be a person or a server
* Eavesdrops on communication between 2 people or servers
* Easy to perform on public network
* Done often by government
Solution?
* Upgrade to HTTPS -> all traffic is encrypted

* Code injection
* For example via SQL
Solution?
* Use paramaterised queries instead of pasting data into query?

Insecure Direct Object Reference
* Guess other ids

AUTHENTIFICATION
* Username, password, fingerprint -> proves that the person is who they say they are

AUTHORIZATION
* Only allow access of certain things to certain users

