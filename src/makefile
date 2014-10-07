sourcefiles = \
chess/models/ChessPiece.java \
chess/models/Board.java \
chess/models/messages/Message.java \
chess/models/Rules.java \
chess/models/User.java \
chess/models/games/Game.java \
chess/models/VanillaChessBoard.java \
chess/models/VanillaChessRules.java \
chess/models/games/VanillaChessGame.java \
chess/views/GameView.java \
chess/views/MainMenuView.java \
chess/views/terminals/ChessTerminal.java \
chess/views/terminals/MainMenuTerminal.java \
chess/views/terminals/VanillaChessTerminal.java \
chess/models/UserManager.java \
chess/controllers/games/GameController.java \
chess/DisplayMode.java 

classfiles  = $(sourcefiles:.java=.class)

all: $(classfiles) App
%.class: %.java
	javac -d . -classpath . $<

App:
	javac -d . -classpath . \
							chess/controllers/MasterController.java \
							chess/controllers/games/VanillaChessController.java \
							chess/controllers/MainMenuController.java \
							chess/Application.java

run:
	java chess/Application

clean:
	rm -f *.class