<table><tr><td> <em>Assignment: </em> IT114 Chatroom Milestone4</td></tr>
<tr><td> <em>Student: </em> Peter Redfern (par36)</td></tr>
<tr><td> <em>Generated: </em> 12/13/2023 11:37:48 PM</td></tr>
<tr><td> <em>Grading Link: </em> <a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-chatroom-milestone4/grade/par36" target="_blank">Grading</a></td></tr></table>
<table><tr><td> <em>Instructions: </em> <p>Implement the features from Milestone3 from the proposal document:&nbsp;&nbsp;<a href="https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view">https://docs.google.com/document/d/1ONmvEvel97GTFPGfVwwQC96xSsobbSbk56145XizQG4/view</a></p>
</td></tr></table>
<table><tr><td> <em>Deliverable 1: </em> Client can export chat history of their current session (client-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot of related UI</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.04.02image.png.webp?alt=media&token=923a94ae-9f4e-4d6f-8685-bb8fee5e1731"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the chatroom with the export button at the top<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.04.46image.png.webp?alt=media&token=667897d9-8672-4630-acb9-dda3063010b7"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the code for the creation of the button<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.05.49image.png.webp?alt=media&token=b371f021-78e6-496d-abcf-3482563a9bd1"/></td></tr>
<tr><td> <em>Caption:</em> <p>The code for what happens when the button is pressed. It gets the<br>time and data for the filename as well as exports it to a<br>folder for HTML chatlog exports. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.18.15image.png.webp?alt=media&token=a9851daa-f5a0-4217-958f-72845acaf254"/></td></tr>
<tr><td> <em>Caption:</em> <p>gets the chat history which goes into the exported file<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.21.52image.png.webp?alt=media&token=1239ddf8-43c3-4b80-a614-1114c26df9aa"/></td></tr>
<tr><td> <em>Caption:</em> <p>creates the button at the top of the chat<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot of exported data</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.11.25image.png.webp?alt=media&token=8c3c3ae9-4a27-43e1-b2da-7eaf68c06151"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the HTML export with correct formatting (same as the original). <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.12.41image.png.webp?alt=media&token=c77c4a18-7dc7-418b-8516-a5f41b55f054"/></td></tr>
<tr><td> <em>Caption:</em> <p>The original text in the chat<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>There is a method called getChatHistory which gets all of the messages sent<br>when the user clicks the button. It does this by converting the JEditorPane<br>text (the chat messages) to a string and then putting them together with<br>a string builder. This method gets called in the FileWriter in the export<br>command which writes the chat history to the file.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 2: </em> Client's mute list will persist across sessions (server-side) </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot of how the mute list is stored</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.23.09image.png.webp?alt=media&token=2d06c303-b13f-4c5e-8b29-3e1bf448dfda"/></td></tr>
<tr><td> <em>Caption:</em> <p>MuteList file showing the format<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.23.32image.png.webp?alt=media&token=b5b279fb-2a74-4c8c-8d7b-626c73b7b74a"/></td></tr>
<tr><td> <em>Caption:</em> <p>The chat when B and C are muted<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-14T04.35.46image.png.webp?alt=media&token=da3156cd-96e9-45e9-912c-4c909fb74c1e"/></td></tr>
<tr><td> <em>Caption:</em> <p>With D acting as the witness user. A muted B and C the<br>first time they joined. They both said &quot;Hi&quot; and left. They then rejoined<br>and said &quot;Hi&quot; again. A still didn&#39;t get any messages, proving the muteList<br>carries over across sessions.<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the code saving/loading mute list</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.25.20image.png.webp?alt=media&token=3a5b289f-287e-4689-9330-f7d7dbb33b91"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows the code that writes the file and loads it in when the<br>room is restarted<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.26.48image.png.webp?alt=media&token=db2a5469-77e4-46a0-9700-020f25cf653a"/></td></tr>
<tr><td> <em>Caption:</em> <p>When the user connects, the muteList is loaded in. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-14T04.25.38image.png.webp?alt=media&token=32e4f84a-9077-43bd-8427-39c4c1daace3"/></td></tr>
<tr><td> <em>Caption:</em> <p>Used by mute and unmute to find a ServerThread from the clientlist by<br>name and gets the first matching name from the list<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>When a user mutes someone, a muteList file is written with the fileWriter.<br>This muteList works in the current session but also across sessions as there<br>is a method that uses a Scanner to read the file in again<br>and repopulate the MuteList in the ServerThread class with the one that was<br>already written. It is called in by the Connect payload as shown in<br>the second screenshot.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 3: </em> Client's will receive a message when they get muted/unmuted by another user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add a screenshot showing the related chat messages</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.51.17image.png.webp?alt=media&token=763f0343-22a7-401e-ba4d-6a5e14cdeb79"/></td></tr>
<tr><td> <em>Caption:</em> <p>The server sent messages showing users B and C that they were muted<br>and unmuted by user A. User D doesn&#39;t get any of the messages<br>because they are not involved. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add a screenshot of the related code snippets</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.52.17image.png.webp?alt=media&token=d12d0095-f394-457b-a314-e3289e827bb9"/></td></tr>
<tr><td> <em>Caption:</em> <p>This is in Room.java and shows the mute/unmute commands and how the messages<br>are sent to just the client muting/unmuting and the receiving party. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <p>The mute and unmute commands look for the user&#39;s name that is affected<br>after the /unmute and /mute command. It stores this as a target variable<br>which is then used along with client.getClientName() to send the messages to the<br>person being muted/unmuted and the person doing it in the first place.&nbsp;<br></p><br></td></tr>
</table></td></tr>
<table><tr><td> <em>Deliverable 4: </em> User list should update per the status of each user </td></tr><tr><td><em>Status: </em> <img width="100" height="20" src="https://user-images.githubusercontent.com/54863474/211707773-e6aef7cb-d5b2-4053-bbb1-b09fc609041e.png"></td></tr>
<tr><td><table><tr><td> <em>Sub-Task 1: </em> Add screenshot for Muted users by the client should appear grayed out</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T18.58.24image.png.webp?alt=media&token=8058fbb4-2b28-463f-8693-98a4ff80066a"/></td></tr>
<tr><td> <em>Caption:</em> <p>Chat showing ChatPanel with user&#39;s names grayed out when muted. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T19.08.49image.png.webp?alt=media&token=44ff2bbb-c913-4d57-b07a-9e506d38c14a"/></td></tr>
<tr><td> <em>Caption:</em> <p>Room.java mute and unmute functions<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T19.09.09image.png.webp?alt=media&token=544c03a0-da07-464d-8c2c-bb68d5da2d01"/></td></tr>
<tr><td> <em>Caption:</em> <p>ServerThread mute and unmute functions. <br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-13T19.10.27image.png.webp?alt=media&token=6a5e0108-9cb3-4b57-bfa9-8e85cbdd36d0"/></td></tr>
<tr><td> <em>Caption:</em> <p>Client.java MUTED and UNMUTED payload cases. <br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 2: </em> Add screenshot for Last person to send a message gets highlighted</td></tr>
<tr><td><table><tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-14T01.10.15image.png.webp?alt=media&token=e5809b71-d046-4f42-b255-0ff3adfef04e"/></td></tr>
<tr><td> <em>Caption:</em> <p>Shows how the highlight works with the mute feature. Both colors are properly<br>displayed in the same panel and do not overlap. Example: Since D muted<br>C and C was the last to speak, C does not show up<br>highlighted for D. B does instead , since B was the last one<br>D could see.<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-14T01.14.18image.png.webp?alt=media&token=5156e5d8-9b8b-450f-abbf-526343a406ca"/></td></tr>
<tr><td> <em>Caption:</em> <p>Code screenshot from ChatPanel which calls the highlight method and applies to the<br>ChatPanel<br></p>
</td></tr>
<tr><td><img width="768px" src="https://firebasestorage.googleapis.com/v0/b/learn-e1de9.appspot.com/o/assignments%2Fpar36%2F2023-12-14T01.16.32image.png.webp?alt=media&token=a779a498-2da7-4cf2-a71a-54abad9cc797"/></td></tr>
<tr><td> <em>Caption:</em> <p>Code screenshot from ClientUI<br></p>
</td></tr>
</table></td></tr>
<tr><td> <em>Sub-Task 3: </em> Briefly explain how you implemented this</td></tr>
<tr><td> <em>Response:</em> <ol><br><li>Muted Clients: The client gets muted through a command in room which<br>then goes to ServerThread. In ServerThread the Client actually gets added to the<br>muteList which tells the UI that the client is muted. From there, UserListPanel<br>uses it&#39;s method to paint the name gray and that color change method<br>is called in ChatPanel which does the change. If the user is removed<br>from the MuteList the process is repeated except a different UserListPanel method changes<br>the color back to normal.&nbsp;<div><br></div><div>2. Message Highlight: UserListPanel has a method called userListNameRefresh<br>which highlights the last message sent by a user. That method then goes<br>to a method called highlightUser which is basically a transport method that then<br>sends it to ClientUI that has a method called onMessageReceive that takes chatPanel.highlightUser<br>and uses it to make the name of the Client that sent the<br>most recent message yellow.&nbsp;</div><br></li><br></ol><br></td></tr>
</table></td></tr>
<table><tr><td><em>Grading Link: </em><a rel="noreferrer noopener" href="https://learn.ethereallab.app/homework/IT114-005-F23/it114-chatroom-milestone4/grade/par36" target="_blank">Grading</a></td></tr></table>