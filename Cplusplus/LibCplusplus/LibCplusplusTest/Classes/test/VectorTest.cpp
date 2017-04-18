// C++LearningVS.cpp : �������̨Ӧ�ó������ڵ㡣
//

//#include "stdafx.h"
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <ctime>
#include <string>

//#include <cstdlib>
#include "VectorTest.h"
using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::vector;
using std::time;


/*ʹ�õ�����*/
void VectorTest::useHeroInventory3() {
    vector<string> inventory;
    inventory.push_back("sword");
    inventory.push_back("armor");
    inventory.push_back("shield");
    //PS:
    //ʹ��push_back����ʹ�������������е�������Ч
    //ʹ��insert��ʹ���������˲����֮���Ԫ�صĵ�����ʧЧ����Ϊ���в����֮���Ԫ�ض�������һλ
    //ʹ��erase��ʹ���������˲����֮���Ԫ�صĵ�����ʧЧ����Ϊ���в����֮���Ԫ�ض�������һλ

    //������������
    //������
    vector<string>::iterator myIterator;
    //����������
    vector<string>::const_iterator iter;

    //ѭ�����ʱ���
    //����inventory�������ַ�������ֵ"sword",���ǰ���string����
    //ʹ��begin��end��Ա����
    //end�������������һ��Ԫ��֮���һ��������
    cout << "Your items:\n";
    for (iter = inventory.begin(); iter != inventory.end(); iter++)	{
        cout << *iter << endl;
    }

    //�޸�����Ԫ�ص�ֵ
    cout << "\nYou trade your sword for a battle axe.";
    myIterator = inventory.begin();
    *myIterator = "battle axe";
    cout << "\nYour items:\n";
    for (iter = inventory.begin(); iter != inventory.end(); iter++)	{
        cout << *iter<<endl;
    }

    //��������Ԫ�صĳ�Ա����
    //һ�����ʹ��->��������ʵ��������ö���ĳ�Ա���������ݳ�Ա
    cout << "\nThe item name '" << *myIterator << "' has ";
    cout << (*myIterator).size() << " letters in it.\n";
    cout << "\nThe item name '" << *myIterator << "' has ";
    cout << myIterator->size() << " letters in it.\n";

    //ʹ��insert��Ա����
    cout << "\nYou recover a crossbow from a slain enemy.";
    inventory.insert(inventory.begin(), "crossbow");
    cout << "\nYour items:\n";
    for (iter = inventory.begin(); iter != inventory.end(); iter++)	{
        cout << *iter << endl;
    }
    //ʹ��erase��Ա����
    cout << "\nYour armor is destoryed in a fierce battle.";
    inventory.erase(inventory.begin() + 2);
    cout << "\nYour items:\n";
    for (iter = inventory.begin(); iter != inventory.end(); iter++)	{
        cout << *iter << endl;
    }
}

void VectorTest::useHeroInventory2() {
    //����������
    vector<string> inventory;
    //ʹ��push_back��Ա����
    inventory.push_back("sword");
    inventory.push_back("armor");
    inventory.push_back("shield");
    //ʹ��size��Ա����
    cout << "You have "<<inventory.size()<<" items.\n";
    //����������
    cout << "Your items:\n";
    for (unsigned int i = 0; i < inventory.size(); i++)	{
        cout << inventory[i] << endl;
    }
    cout << "\nYou trade your sword for a battle axe.";
    inventory[0] = "battle axe";
    cout << "\nYour items:\n";
    for (unsigned int i = 0; i < inventory.size(); i++)	{
        cout << inventory[i] << endl;
    }
    //����Ԫ�صĳ�Ա����
    cout << "\nThe item name '" << inventory[0] << "' has ";
    cout << inventory[0].size() << " letters in it.\n";
    //����pop_back��Ա����
    cout << "\nYour shiled is destoryed in a fierce battle.";
    inventory.pop_back();
    cout << "\nYour items:\n";
    for (unsigned int i = 0; i < inventory.size(); i++)	{
        cout << inventory[i] << endl;
    }
    //����clear��Ա����
    cout << "You are robbed of all of your possessions by a thief.";
    inventory.clear();
    //����empty��Ա����
    if (inventory.empty()) {
        cout << "\nYou have nothing.\n";
    } else {
        cout << "\nYou have at latest one item.\n";
    }
}

/*ʹ���㷨*/
void VectorTest::useAlgorithm() {
    vector<int>::const_iterator iter;
    cout << "Creating a list of scores.";
    vector<int> scores;
    scores.push_back(1500);
    scores.push_back(3500);
    scores.push_back(7500);
    cout << "\nHigh Scores:\n";
    for (iter =scores.begin(); iter !=scores.end(); iter++)	{
        cout << *iter << endl;
    }

    //ʹ��find�㷨
    //STL��find�㷨��ָ����Χ�ڵ�������Ԫ���в���ֵ�����������õ�һ��ƥ��Ԫ�ص�һ�������������û���ҵ�ƥ���Ԫ�أ��򷵻ص� ������ָ��ָ����Χ�Ľ�β����
    cout << "\nFinding a score.";
    int score;
    cout << "\nEnter a score to find:";
    cin >> score;
    iter = find(scores.begin(), scores.end(),score);
    if (iter != scores.end())	{
        cout << "Score found.\n";
    } else {
        cout << "Score not found.\n";
    }

    //ʹ��random_shuffle�㷨
    //random_shuffle�㷨�������е�Ԫ�ؽ������򡣸��㷨��Ҫ���е������������յ�������������������
    cout << "\nRandomizing scores.";
    srand(static_cast<unsigned int>(time(0)));
    random_shuffle(scores.begin(),scores.end());
    cout << "\nHigh Scores:\n";
    for (iter = scores.begin(); iter != scores.end(); iter++)	{
        cout << *iter << endl;
    }

    //ʹ��sort�㷨���������е�Ԫ�ؽ����������򡣸��㷨��Ҫ���е������������յ�������������������
    cout << "\nSorting scores.";
    sort(scores.begin(),scores.end());
    cout << "\nHigh Scores:\n";
    for (iter = scores.begin(); iter != scores.end(); iter++)	{
        cout << *iter << endl;
    }

    //ֻҪ���������ض�Ҫ��Ϳ���ʹ��STL�㷨
    //���ַ��������������
    string word = "high score";
    random_shuffle(word.begin(),word.end());
}

void VectorTest::useHangman() {
    const int MAX_WRONG = 8;
    vector<string> words;
    words.push_back("GUESS");
    words.push_back("HANGMAN");
    words.push_back("DIFFICULT");
    srand(static_cast<unsigned int>(time(0)));
    random_shuffle(words.begin(),words.end());
    const string THE_WORD = words[0];
    int wrong = 0;
    string soFar(THE_WORD.size(),'-');
    string used = "";
    cout << "Welcome to Hangman.Good luck.\n";

    while ((wrong<MAX_WRONG)&&(soFar!=THE_WORD))	{
        cout << "\nYou have "<<(MAX_WRONG-wrong);
        cout << " incorrect guesses left.\n";
        cout << "\nYou've used the following letters:\n"<<used<<endl;
        cout << "\nSo far,the word is:\n"<<soFar<<endl;

        char guess;
        cout << "\n\nEnter your guess:";
        cin >> guess;
        guess = toupper(guess);
        while (used.find(guess)!=string::npos)		{
            cout << "\nYou've already guessed " << guess << endl;
            cout << "\n\nEnter your guess:";
            cin >> guess;
            guess = toupper(guess);
        }
        used += guess;
        if (THE_WORD.find(guess)!=string::npos)	{
            cout << "That's right!" << guess << " is in the word.\n";
            for (unsigned int i = 0; i < THE_WORD.size(); i++)			{
                if (THE_WORD[i]==guess)	{
                    soFar[i] = guess;
                }
            }
        } else {
            cout << "Sorry," << guess << " isn't in the word.\n";
            wrong++;
        }
    }
    if (wrong==MAX_WRONG) {
        cout << "\nYou've been hanged!";

    } else {
        cout << "\nYou guessed it!";
    }
    cout << "\nThe word was " << THE_WORD << endl;
}


void VectorTest::test() {
    //useHeroInventory2();
    //useHeroInventory3();
    //useAlgorithm();
    useHangman();
    //system("pause");yon g
}

/**ʹ������*/
//int _tmain(int argc, _TCHAR* argv[]) {
//
//    return EXIT_SUCCESS;
//}