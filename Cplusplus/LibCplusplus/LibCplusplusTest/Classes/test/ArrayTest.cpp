// C++LearningVS.cpp : �������̨Ӧ�ó������ڵ㡣
//

//#include "stdafx.h"
#include "ArrayTest.h"
#include <iostream>
#include <string>
#include <ctime>
#include <cstdlib>

using std::cout;
using std::endl;
using std::cin;
using std::string;
//using std::time;
//using std::rand;

/*Word Jumble game*/
void ArrayTest::useWordJumble() {
    //�ֶ���
    enum fields	{ WORD,HINT,NUM_FIELDS };
    const int NUM_WORDS = 5;
    const string WORDS[NUM_WORDS][NUM_FIELDS] {
        {"wall","Do you feel you're banging your head egainst something."},
        { "glasses", "These might help you see the answer." },
        { "labored", "Going slowly,is it?" },
        { "persistent", "Keep at it." },
        { "jumble", "It's what the game is all about." },
    };
    //���ѡ�񵥴�
    srand(static_cast<unsigned int>(time(0)));
    int choice = (rand() % NUM_WORDS);
    string theWord = WORDS[choice][WORD];
    string theHint = WORDS[choice][HINT];
    //��������
    string jumble = theWord;
    int length = jumble.size();
    for (int i = 0; i < length; i++) {
        int index1 = (rand() % length);
        int index2 = (rand() % length);
        char temp = jumble[index1];
        jumble[index1] = jumble[index2];
        jumble[index2] = temp;
    }
    //��ӭ����
    cout << "\t\tWelcome to Word Jumble!\n\n";
    cout << "Unscramble the letters to make a word.\n";
    cout << "Enter 'hint' for a hint.\n";
    cout << "Enter 'quit' to quit the game..\n\n";
    cout << "The jumble is: " << jumble;
    string guess;
    cout << "\n\nYour guess:";
    cin >> guess;
    //������Ϸ��ѭ��
    while ((guess != theWord)&&(guess != "quit"))	{
        if (guess == "hint") {
            cout << theHint;
        } else {
            cout << "Sorry,that's not it.";
        }
        cout << "\n\nYour guess:";
        cin >> guess;
    }
    //��Ϸ����
    if (guess == theWord) {
        cout << "\nThat's it!You guessed it!\n";
    }
    cout << "\nThanks for playing.\n";
}

/*ʹ�ö�ά����*/
void ArrayTest::useMultidimensionalArray() {
    const int ROWS = 3;
    const int COLUMNS = 3;
    //������ά����
    char board[ROWS][COLUMNS] = { { 'O', 'X', 'O' },
        { ' ', 'X', 'X' },
        { 'X', 'O', 'O' }
    };
    //��ά���������
    cout << "\n\nHere's the tic-tac-toe baord:\n";
    for (int i = 0; i < ROWS; i++)	{
        for (int j = 0; j < COLUMNS; j++) {
            cout << board[i][j];
        }
        cout << endl;
    }

    cout << "\n'X' moves to the empty location.\n\n";
    board[1][0] = 'X';
    cout << "Noew the tic-tac-toe baord is:\n";
    for (int i = 0; i < ROWS; i++)	{
        for (int j = 0; j < COLUMNS; j++) {
            cout << board[i][j];
        }
        cout << endl;
    }
    cout << "\n'X' wins!";
}

/*��������*/
void ArrayTest::useBaseArray() {
    //��������
    const int MAX_ITEMS = 10;
    string inventory[MAX_ITEMS];
    //���������
    int numItems = 0;
    inventory[numItems++] = "sword";
    inventory[numItems++] = "armor";
    inventory[numItems++] = "shield";
    cout << "Your items:\n";
    for (int i = 0; i < numItems; i++)	{
        cout << inventory[i] << endl;
    }
    cout << "\nYou trade your sword for a battle axe.";
    inventory[0] = "battle axe";
    cout << "\nYour items:\n";

    for (int i = 0; i < numItems; i++)	{
        cout << inventory[i] << endl;
    }
    //ʹ������Ԫ�صĳ�Ա����
    cout << "\nThe item name '" << inventory[0] << "' has ";
    cout << inventory[0].size() << " letters in it.\n";
    cout << "\nYou find a healing potion.";
    //����߽�
    if (numItems<MAX_ITEMS)	{
        inventory[numItems++] = "healing potion";
    } else {
        cout << "You has too many items and can't carry another.";
    }
    cout << "Your items:\n";
    for (int i = 0; i < numItems; i++)	{
        cout << inventory[i] << endl;
    }
}

/*���C����ַ���*/
void ArrayTest::understandCStyleString() {
    string word1 = "Game";
    char word2[] = "Over";
    string phrase = word1+" "+word2;
    if (word1 != word2)	{
        cout << "word1 and word2 are not equal.";
    }
    if (phrase.find(word2) == string::npos)	{
        cout << "word2 is contained in phrase.\n";
    }
}

//PS:�ڵ���useMultidimensionalArray֮ǰ��Ҫ������useMultidimensionalArray�������ķ�ʽ�� ��useMultidimensionalArray��ǰ��main()֮ǰ��
/**ʹ������*/
//int _tmain(int argc, _TCHAR* argv[]) {
//    useBaseArray();
//    useMultidimensionalArray();
//    useWordJumble();
//    //system("pause");yon g
//    return EXIT_SUCCESS;
//}

void ArrayTest::test() {
    useBaseArray();
    useMultidimensionalArray();
    useWordJumble();
    //system("pause");yon g
}