#include <SFML/Graphics.hpp>
#include <iostream>
#include <vector>
#include "TextureManager.h"
#include "Random.h"
#include "Tile.h"
#include "Board.h"
using namespace std;
using namespace sf;

int main() 
{	
    /*======Initialization======*/
    RenderWindow window(sf::VideoMode(800, 600), "Minesweeper"); //Creates the Minesweeper window
    
    /*====Texture Stage====*/

    //Start of Buttons
    TextureManager::LoadTexture("debug");
    TextureManager::LoadTexture("face_happy");
    TextureManager::LoadTexture("face_lose");
    TextureManager::LoadTexture("face_win");
    for (int i = 1; i <= 3; i++)//Loads textures for tests 1-3
    {
        string num = "test_" + to_string(i);
        TextureManager::LoadTexture(num.c_str());
    }
    //End of Buttons

    //Start of Tile Related
    TextureManager::LoadTexture("flag");
    TextureManager::LoadTexture("mine");
    TextureManager::LoadTexture("tile_hidden");
    TextureManager::LoadTexture("tile_revealed");
    //End of Tile Related
    
    //Start of numbers/digits
    for (int i = 1; i <= 8; i++)//Loads textures for numbers 1-8
    {
        string num = "number_" + to_string(i);
        TextureManager::LoadTexture(num.c_str());
    }
    TextureManager::LoadTexture("digits");
    //End of numbers/digits

    /*====Sprite Stage====*/
    vector<Sprite> tile_sprites;
    for (int i = 0; i < 16; i++)
    {
        for (int j = 0; j < 25; j++)
        {
            Sprite tile(TextureManager::GetTexture("tile_hidden"));
            tile.setPosition(j*32.0f,i*32.0f);
            tile_sprites.push_back(tile);
        }
    }

    //Sprites for all the buttons and their positons
    Sprite debug(TextureManager::GetTexture("debug"));
    Sprite test_one(TextureManager::GetTexture("test_1"));
    Sprite test_two(TextureManager::GetTexture("test_2"));
    Sprite test_three(TextureManager::GetTexture("test_3"));
    Sprite reset(TextureManager::GetTexture("face_happy"));
    reset.setPosition(11.5 * 32, 512);
    debug.setPosition(15.5 * 32, 512);
    test_one.setPosition(17.5 * 32, 512);
    test_two.setPosition(19.5 * 32, 512);
    test_three.setPosition(21.5 * 32, 512);
    //End of all button sprites
 
    //Board initialization and extra board variables
    Board board = Board();
    bool gameChk = true;
    bool debugChk = false;
    //Window
    while (window.isOpen())
    {
        Event event;
        while (window.pollEvent(event))
        {
            // "close requested" event: we close the window FROM SFML WEBSITE
            if (event.type == Event::Closed)
                window.close();
            //If a mouse button is pressed
            if (event.type == Event::MouseButtonPressed)
            {
                int index = event.mouseButton.x / 32 + event.mouseButton.y / 32 * 25;
                //Left Click
                if (event.mouseButton.button == Mouse::Left)
                {
                    //Tile Revealing Mechanic
                    if(index < 400 && !board.tiles[index].isRevealed && !board.tiles[index].isFlagged && gameChk) 
                    {
                        board.tiles[index].isRevealed = true; //Reveals tile
                        //Mine Found (Game Over)
                        if (board.tiles[index].isMine == true)
                        {
                            for (int i = 0; i < 400; i++)
                            {
                                if (board.tiles[i].isMine)
                                {
                                    reset.setTexture(TextureManager::GetTexture("face_lose"));
                                    gameChk = false;
                                    board.tiles[i].isRevealed = true;
                                }
                            }
                        }
                        //Nearby Mines
                        else
                        { 
                            int counter = 0;
                            board.tiles[index].MineAmount();
                            for (int i = 0; i < 400; i++)
                            {
                                if (board.tiles[i].isRevealed)
                                {
                                    counter++;
                                }
                            }
                            if (400 - counter - board.mineCount == 0)
                            {
                                reset.setTexture(TextureManager::GetTexture("face_win"));
                                gameChk = false;
                                for (int i = 0; i < 400; i++)
                                {
                                    if (board.tiles[i].isMine)
                                        board.tiles[i].isFlagged = true;
                                }
                            }
                        }
                    }
                    //Reset Mechanic
                    if (event.mouseButton.x >= 368 && event.mouseButton.x <= 432 && event.mouseButton.y >= 512 && event.mouseButton.y <= 576)
                    {
                        gameChk = true;
                        board.Reset();
                        for (int i = 0; i < 400; i++)
                        {
                            tile_sprites[i].setTexture(TextureManager::GetTexture("tile_hidden"));
                        }
                        reset.setTexture(TextureManager::GetTexture("face_happy"));
                    }
                    //Debug Mechanic
                    if (event.mouseButton.x >= 496 && event.mouseButton.x < 560 && event.mouseButton.y >= 512 && event.mouseButton.y <= 576)
                    {
                        if (debugChk)
                            debugChk = false;
                        else
                            debugChk = true;
                    }
                    
                    //Test 1 Loaded
                    if (event.mouseButton.x >= 560 && event.mouseButton.x < 624 && event.mouseButton.y >= 512 && event.mouseButton.y <= 576)
                    {
                        gameChk = true;
                        board.Load("boards/testboard1.brd");
                        for (int i = 0; i < 400; i++)
                        {
                            tile_sprites[i].setTexture(TextureManager::GetTexture("tile_hidden"));
                        }
                        reset.setTexture(TextureManager::GetTexture("face_happy"));
                    }

                    //Test 2 Loaded
                    if (event.mouseButton.x >= 624 && event.mouseButton.x < 688 && event.mouseButton.y >= 512 && event.mouseButton.y <= 576)
                    {
                        gameChk = true;
                        board.Load("boards/testboard2.brd");
                        for (int i = 0; i < 400; i++)
                        {
                            tile_sprites[i].setTexture(TextureManager::GetTexture("tile_hidden"));
                        }
                        reset.setTexture(TextureManager::GetTexture("face_happy"));
                    }

                    //Test 3 Loaded
                    if (event.mouseButton.x >= 688 && event.mouseButton.x < 752 && event.mouseButton.y >= 512 && event.mouseButton.y <= 576)
                    {
                        gameChk = true;
                        board.Load("boards/testboard3.brd");
                        for (int i = 0; i < 400; i++)
                        {
                            tile_sprites[i].setTexture(TextureManager::GetTexture("tile_hidden"));
                        }
                        reset.setTexture(TextureManager::GetTexture("face_happy"));
                    }
                    
                }

                //Right Click
                if (event.mouseButton.button == Mouse::Right)
                {
                    //Flagging Mechanic

                    if (index < 400 && !board.tiles[index].isFlagged && !board.tiles[index].isRevealed && gameChk)
                    {
                        board.tiles[index].isFlagged = true;
                    }
                    else if (index < 400 && board.tiles[index].isFlagged && gameChk)
                    {
                        board.tiles[index].isFlagged = false;
                    }
                }


            }
        }
        window.clear(Color::White); //Cleared to white
        
        
        //Draws the unrevealed or revealed tile
        for (int i = 0; i < 400; i++)
        {
            if (board.tiles[i].isRevealed)
            {
                tile_sprites[i].setTexture(TextureManager::GetTexture("tile_revealed"));
            }
            window.draw(tile_sprites[i]);
        }

        //Draws the value (if there is one)
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 25; j++)
            {
                if (board.tiles[25 * i + j].isRevealed && board.tiles[25 * i + j].nearbyMineAmount != 0)
                {
                    string num = "number_" + to_string(board.tiles[25 * i + j].nearbyMineAmount);
                    Sprite value(TextureManager::GetTexture(num.c_str()));
                    value.setPosition(j * 32.0f, i * 32.0f);
                    window.draw(value);
                }
            }
        }
        
        //Draws the flag, if the tile is meant to be flagged
        for (int i = 0; i < 16; i++)
        {
            for (int j = 0; j < 25; j++)
            {
                if (board.tiles[25 * i + j].isFlagged)
                {
                    Sprite flag(TextureManager::GetTexture("flag"));
                    flag.setPosition(j*32.0f,i*32.0f);
                    window.draw(flag);
                }
            }
        }
        for (int i = 0; i < 400; i++)
        {
            
            //Draws the mines and removes any flags on said mine (unless in debug)
            if ( (board.tiles[i].isRevealed || debugChk) && board.tiles[i].isMine)
            {
                Sprite mine(TextureManager::GetTexture("mine"));
                mine.setPosition(tile_sprites[i].getPosition());
                window.draw(mine);
            }
        }
        //Draws all the buttons
        window.draw(reset);
        window.draw(debug);
        window.draw(test_one);
        window.draw(test_two);
        window.draw(test_three);
        //Draws the mine counter
        int mineDisplay = board.mineCount;
        int offset = 0;
        for (int i = 0; i < 400; i++)
        {
            if (board.tiles[i].isFlagged)
                mineDisplay--;
        }
        if (mineDisplay < 0)
        {
            Sprite negative(TextureManager::GetTexture("digits"));
            negative.setTextureRect(IntRect(210, 0, 21, 32));
            negative.setPosition(offset*1.0f,512.0f);
            window.draw(negative);
            offset += 21;
        }
        for (int i = 100; i > 0; i /= 10)
        {
            Sprite num(TextureManager::GetTexture("digits"));
            num.setTextureRect(IntRect(21 * (abs(mineDisplay) / i), 0, 21, 32));
            num.setPosition(offset*1.0f, 512.0f);
            window.draw(num);
            offset += 21;
            mineDisplay %= i;
        }
        //Display
        window.display(); 
        
    }
    TextureManager::Clear();
    return 0;
}