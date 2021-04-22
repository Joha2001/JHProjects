#include "Board.h"
#include <iostream>

Board::Board()
{
	for (int i = 0; i < 400; i++)
	{
		tiles.push_back(Tile());
	}
	SetAdjTiles();
	mineCount = 0;
	while (mineCount != 50)
	{
		int rand = Random::Int(0, 399);
		if (!tiles[rand].isMine)
		{
			tiles[rand].isMine = true;
			mineCount++;
		}
	}
}

void Board::Reset()
{
	tiles.clear();
	for (int i = 0; i < 400; i++)
	{
		tiles.push_back(Tile());
	}
	SetAdjTiles();
	mineCount = 0;
	while (mineCount != 50)
	{
		int rand = Random::Int(0, 399);
		if (!tiles[rand].isMine)
		{
			tiles[rand].isMine = true;
			mineCount++;
		}
	}
}

void Board::Load(const char* filename)
{
	tiles.clear();
	mineCount = 0;
	std::ifstream inFile;
	inFile.open(filename);
	while (!inFile.eof())
	{
		std::string value;
		getline(inFile, value);
		for (unsigned int i = 0; i < value.size(); i++)
		{
			tiles.push_back(Tile(stoi(value.substr(i, 1))));
			if (stoi(value.substr(i, 1)) == 1)
				mineCount++;
		}
	}
	SetAdjTiles();
	inFile.close();
}

void Board::SetAdjTiles()
{
	/*====Non-Border Tiles====*/
	for (int i = 1; i < 15; i++)
	{
		for (int j = 1; j < 24; j++)
		{
			tiles[25 * i + j].adjTiles.push_back(&tiles[(25 * i + j) - 1]); //West Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[(25 * i + j) + 1]); //East Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[25 * (i + 1) + j]); //South Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[25 * (i - 1) + j]); //North Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[(25 * (i + 1) + j) - 1]); //SW Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[(25 * (i + 1) + j) + 1]); //SE Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[(25 * (i - 1) + j) - 1]);//NW Adjacent
			tiles[25 * i + j].adjTiles.push_back(&tiles[(25 * (i - 1) + j) + 1]);//NE Adjacent
		}
	}
	/*====Border but Not Corner Tiles====*/
	//First Row
	for (int j = 1; j < 24; j++)
	{
		tiles[j].adjTiles.push_back(&tiles[j - 1]); //West Adjacent
		tiles[j].adjTiles.push_back(&tiles[j + 1]); //East Adjacent
		tiles[j].adjTiles.push_back(&tiles[25 + j]); //South Adjacent
		tiles[j].adjTiles.push_back(&tiles[(25 + j) - 1]); //SW Adjacent
		tiles[j].adjTiles.push_back(&tiles[(25 + j) + 1]); //SE Adjacent

	}
	//Last Row
	for (int j = 1; j < 24; j++)
	{
		tiles[375 + j].adjTiles.push_back(&tiles[(375 + j) - 1]); //West Adjacent
		tiles[375 + j].adjTiles.push_back(&tiles[(375 + j) + 1]); //East Adjacent
		tiles[375 + j].adjTiles.push_back(&tiles[350 + j]); //North Adjacent
		tiles[375 + j].adjTiles.push_back(&tiles[(350 + j) - 1]); //NW Adjacent
		tiles[375 + j].adjTiles.push_back(&tiles[(350 + j) + 1]); //NE Adjacent
	}
	//First Column
	for (int i = 1; i < 15; i++)
	{

		tiles[25 * i].adjTiles.push_back(&tiles[(25 * i) + 1]); //East Adjacent
		tiles[25 * i].adjTiles.push_back(&tiles[25 * (i + 1)]); //South Adjacent
		tiles[25 * i].adjTiles.push_back(&tiles[25 * (i - 1)]); //North Adjacent
		tiles[25 * i].adjTiles.push_back(&tiles[(25 * (i + 1)) + 1]); //SE Adjacent
		tiles[25 * i].adjTiles.push_back(&tiles[(25 * (i - 1)) + 1]); //NE Adjacent
	}
	//Last Column
	for (int i = 1; i < 15; i++)
	{
		tiles[25 * i + 24].adjTiles.push_back(&tiles[((25 * i) + 24) - 1]); //West Adjacent
		tiles[25 * i + 24].adjTiles.push_back(&tiles[25 * (i + 1) + 24]); //South Adjacent
		tiles[25 * i + 24].adjTiles.push_back(&tiles[25 * (i - 1) + 24]); //North Adjacent
		tiles[25 * i + 24].adjTiles.push_back(&tiles[(25 * (i + 1) + 24) - 1]); //SW Adjacent
		tiles[25 * i + 24].adjTiles.push_back(&tiles[(25 * (i - 1) + 24) - 1]); //NW Adjacent
	}
	/*====Corner Tiles====*/
	
	//0
	tiles[0].adjTiles.push_back(&tiles[1]); //East Adjacent
	tiles[0].adjTiles.push_back(&tiles[25]); //South Adjacent
	tiles[0].adjTiles.push_back(&tiles[26]); //SE Adjacent
	//24
	tiles[24].adjTiles.push_back(&tiles[23]); //West Adjacent
	tiles[24].adjTiles.push_back(&tiles[49]); //South Adjacent
	tiles[24].adjTiles.push_back(&tiles[48]); //SW Adjacent
	//375
	tiles[375].adjTiles.push_back(&tiles[376]); //East Adjacent
	tiles[375].adjTiles.push_back(&tiles[350]); //North Adjacent
	tiles[375].adjTiles.push_back(&tiles[351]); //NE Adjacent
	//399
	tiles[399].adjTiles.push_back(&tiles[398]); //West Adjacent
	tiles[399].adjTiles.push_back(&tiles[374]); //North Adjacent
	tiles[399].adjTiles.push_back(&tiles[373]); //NW Adjacent
}