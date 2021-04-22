#include "Tile.h"

Tile::Tile()
{
	nearbyMineAmount = 0;
	isFlagged = false;
	isMine = false;
	isRevealed = false;
}

Tile::Tile(int isAMine)
{
	nearbyMineAmount = 0;
	isRevealed = false;
	isFlagged = false;
	if (isAMine == 1)
	{
		isMine = true;
	}
	else
	{
		isMine = false;
	}
}

void Tile::MineAmount()
{
	isRevealed = true;
	for (int i = 0; i < adjTiles.size(); i++)
	{
		if (adjTiles[i]->isMine)
		{
			nearbyMineAmount++;
		}
	}
	for (int i = 0; i < adjTiles.size(); i++)
	{
		if (!adjTiles[i]->isFlagged && !adjTiles[i]->isRevealed && nearbyMineAmount == 0 && !adjTiles[i]->isMine)
		{
			adjTiles[i]->MineAmount();
		}
	}
}