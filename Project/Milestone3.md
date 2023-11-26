<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone3</td></tr>
<tr><td> <em>Student: </em> Peter Redfern (par36)</td></tr>
<tr><td> <em>Generated: </em> 11/25/2023 8:57:51 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-chatroom-milestone3/grade/par36" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Connection Screens </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing the screens with the following data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T19.13.56image.png.webp?alt=media&token=5d3ea60c-88cb-4250-9533-d6d38b36c2e4"/></td></tr>
<tr><td> <em>Caption:</em> <p>Panel showing the host IP and port number.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T19.14.52image.png.webp?alt=media&token=3d8e4cbb-f0f7-42cc-98ef-55001e03fbaa"/></td></tr>
<tr><td> <em>Caption:</em> <p>Panel showing the username input.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain the code for each step of the process</td></tr>
<tr><td> <em>Response:</em> <div>- The process starts when the user runs ClientUI.java.&nbsp;<br></div><div>- From there, ConnectionPanel.java is<br>launched and the user connects to the IP address and the port of<br>the server they're looking for.</div><div>- Once the user hits next, UserInputPanel.java pops up<br>and the user enters their name.&nbsp;</div><div>- From there, the connection heads off to<br>Room.java, which ends this part of the cycle.&nbsp;</div><div>- ClientUI manages when all of<br>the different panels show up and contains the common features between all of<br>the UI elements. ServerThread and Server manage/display the information to the clients.&nbsp;</div><div><br></div><div><br></div><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Chatroom view </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing the related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.23.55image.png.webp?alt=media&token=31566dc3-4178-4a40-8f88-96f111aff342"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the chatroom with the three users listed on the right side, the<br>messages are sent from each user and recorded in the chatroom, at the<br>bottom, the textbox with the send button is there. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.28.47image.png.webp?alt=media&token=3c8257fb-6827-4898-8a6e-286a4dd2c0cc"/></td></tr>
<tr><td> <em>Caption:</em> <p>Creates the button and the &quot;Send&quot; value that is inside of it. Then<br>inside of a statement, has the processing for checking if the enter key<br>is typed, and if it is, to send the message. (NOTE: This code<br>is given by the professor which is why there is no UCID comment,<br>it is not my code). <br></p>
</td></tr>
</table></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Chat Activities </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Show screenshots of the result of the following commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.36.03image.png.webp?alt=media&token=4e6ffc13-3959-4f92-9838-167f9514c8e2"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows all of the users doing the commands, they are printed out to<br>show who did it and what the result was (for /roll it shows<br>the number of dice as well). The text is in bold and not<br>normal text like the first messages shown above. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show the code snippets for each command</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.39.47image.png.webp?alt=media&token=38b4ba50-31e8-47e4-ad0d-e2b316c845ee"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows how the roll command is taken in and processed. There is one<br>case for a single number and another for multiple dice with the &quot;#d#&quot;<br>format. Both scenarios have different types of math done to calculate the result<br>and two different result variables, one for each case to be printed at<br>the end (in bold). <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.41.29image.png.webp?alt=media&token=d333302a-6350-488c-bcff-4fe3ed33ef32"/></td></tr>
<tr><td> <em>Caption:</em> <p>Makes a random generator which outputs zero or one. If the result from<br>the random number generator is zero then it is head but if it<br>is one then the result is tails. It then saves this to a<br>result variable and prints it out (in bold). <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.45.49image.png.webp?alt=media&token=865329f7-21f8-4bf2-8036-af968a054129"/></td></tr>
<tr><td> <em>Caption:</em> <p>Uses regex to replace the designated command triggers with the HTML tags for<br>each respective font/color type which in the UI will output the message with<br>the proper changes. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-25T22.46.26image.png.webp?alt=media&token=ddbaaca0-819a-4884-a94f-3841d772e088"/></td></tr>
<tr><td> <em>Caption:</em> <p>Has a &quot;message = messageFormat&quot; in sendMessage to change each message to the<br>message with the right font/characteristics. It calls back to the messageFormat in the<br>first screenshot. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the code flow of each command</td></tr>
<tr><td> <em>Response:</em> <ol><br><li>flip - It is added as a command in processCommands and is<br>activated with a / (/flip) like the other commands. It uses a random<br>generator which outputs zero or one. If the result is zero then it<br>flips to heads but if it is one then the coinflip is tails.<br>To get it to print out in bold, the asymmetric triggers for bold<br>&quot;<em>$ $</em>&quot; are put in parenthesis and then concatenated at the beginning and<br>end of the message.&nbsp;<div><br></div><div>2. roll -&nbsp;It is added as a command in processCommands<br>and is activated with a / (/roll) like the other commands. It takes<br>a look at the user input after /roll, if the user just input<br>a number, it will go to the &quot;else&quot; case, take that number and<br>&quot;roll&quot; a die with that number of sides through the math to get<br>the result and output it. However, if the user does it in an<br>&quot;#d#&quot; format, the result takes in the two numbers (one before and after)<br>around d to determine the number of dice and the amount of sides<br>and then roll them and output the total to the user. To get<br>it to print out in bold, the asymmetric triggers for bold &quot;<em>$ $</em>&quot;<br>are put in parenthesis and then concatenated at the beginning and end of<br>the message.&nbsp;</div><div><br></div><div>3. text format - The commands/formatting for the text format is contained<br>inside of the String messageFormat, where each newMessage (message with special changes) is<br>changed according to the brackets that designate what font or color the message<br>should be, they are asymmetrical and wrap around the message. The brackets then<br>become HTML code for the correct formatting with help from regex through the<br>method replaceAll.&nbsp; The finished message is then passed into sendMessage where it is<br>sent out to the UI which displays the correct changed information to match<br>the brackets applied to it (bold, color, multiple, etc.).</div><div><br></div><div>4. Code flow - For<br>all of these commands, they are executed in Room and that&#39;s where the<br>logic is also done. From there, they go off to the Server and<br>then from the server to ServerThread which then sends the data to all<br>all clients in the room/lobby that they are in, which means the information<br>shows up in the UI which then displays the messages (potentially) with their<br>various properties (different font, command, etc.).&nbsp;</div><br></li><br></ol><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> Custom Text </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Screenshots of examples</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T00.57.31image.png.webp?alt=media&token=28e955a5-eeb4-4dc8-a258-2d825da73f21"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the text in this order: Bold, underlined, italic, green (color), and blue,<br>underlined, bold, and italic (everything). <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Briefly explain how you got the UI side to render the text accordingly</td></tr>
<tr><td> <em>Response:</em> <p>Using the substitute wrappers from messageFormat and replaceAll, the wrappers get converted to<br>HTML wrappers instead of the custom ones that are first inputted. With the<br>text now being HTML through conversion, it is then processed by ChatPanel once<br>you change the parameter at the end of the textContainer to &quot;text/html&quot; instead<br>of it&#39;s original value of &quot;text/plain&quot;, as the wrappers input by the user<br>are converted to HTML wrappers/tags through the code and then displayed properly by<br>the UI.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 5: </em> Whisper </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots showing a demo of whisper commands</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.14.30image.png.webp?alt=media&token=fe2b78a6-531a-495b-a7c0-5b142d2ca751"/></td></tr>
<tr><td> <em>Caption:</em> <p>All clients joined and sent messages before which were seen by everyone. Fred<br>couldn&#39;t see the private messages that Matt and Peter sent to each other,<br>proving that the feature worked. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Show the server-side code snippets of how this feature works</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.18.19image.png.webp?alt=media&token=36c3140b-baf2-46fa-a56e-0ad155d6c55d"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code for the sendPrivateMessage method which takes in a parameter of the<br>sender and the message. It checks through the client list for the specified<br>name in the command and then sends the message to only that person<br>and the person sending it. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the code logic of how this was achieved</td></tr>
<tr><td> <em>Response:</em> <p>How it works is that split is used to take the person&#39;s username<br>after the &quot;@&quot; and it uses that name as the target variable. The<br>code then iterates through the list of clients and finds the one with<br>that name, it then sends the message to them and marks the sent<br>message Boolean to true. The message is already seen by the sender as<br>they are the one sending it out.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 6: </em> Mute/Unmute </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshots demoing this feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.33.03image.png.webp?alt=media&token=d39e917e-7135-40e0-af09-4a2a76d23823"/></td></tr>
<tr><td> <em>Caption:</em> <p>Three users are present and Matt&#39;s messages are not sent after Peter uses<br>/mute. Fred still gets Matt&#39;s messages because he doesn&#39;t have Matt muted. There<br>is nothing sent after using mute/unmute (not required) but it probably would help.<br><br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshots of the code snippets that achieve this feature</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.37.50image.png.webp?alt=media&token=99dc585b-86ac-47f4-8902-0a9c5256f075"/></td></tr>
<tr><td> <em>Caption:</em> <p>The methods for mute, unmute and a boolean, isMuted which checks if someone<br>is muted or not. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.38.09image.png.webp?alt=media&token=4ab18c12-5478-4c9a-a02a-aecc4c1cff71"/></td></tr>
<tr><td> <em>Caption:</em> <p>MuteList at the top of ServerThread which holds the names of muted people.<br><br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.38.56image.png.webp?alt=media&token=a39a5f5b-f32e-4275-b7ed-a7f85220c8a3"/></td></tr>
<tr><td> <em>Caption:</em> <p>sendMessage contains an if statement to check if the client is in the<br>muteList. If they are, the message isn&#39;t sent. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-11-26T01.39.16image.png.webp?alt=media&token=dd2fb741-dcbb-4de8-9812-c4f4a97148a2"/></td></tr>
<tr><td> <em>Caption:</em> <p>sendPrivateMessage contains the same if statement which checks if a client is in<br>the muteList. If they are on the list the message isn&#39;t sent. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain the code logic of how this was achieved</td></tr>
<tr><td> <em>Response:</em> <ol><br><li>ServerThread - The muteList in ServerThread holds the names of all of<br>the muted people. The mute method checks, when the command is called, if<br>someone&#39;s name is on the list or not, if it isn&#39;t on the<br>list, it is added. The unmute method checks if someone&#39;s name is on<br>the list, if it is on the list, it is removed. isMuted is<br>a Boolean which checks if the person is on the list or not<br>and returns true or false accordingly.&nbsp;<div><br></div><div>2. Room - In the sendMessage and sendPrivateMessage<br>methods, they use the isMuted Boolean to check if someone is on the<br>muteList or not. If the person is on the list, (true) then the<br>message isn&#39;t sent, however if the person isn&#39;t on the list (false), the<br>message isn&#39;t sent.&nbsp;</div><br></li><br></ol><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 7: </em> Misc </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Pull request from milestone3 to main</td></tr>
<tr><td> <a rel="noreferrer noopener" target="_blank" href="https://github.com/PeterRedfern/par36-it114-005/pull/8">https://github.com/PeterRedfern/par36-it114-005/pull/8</a> </td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-chatroom-milestone3/grade/par36" target="_blank">Grading</a></td></tr></table>