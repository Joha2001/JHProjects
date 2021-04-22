#include "TextureManager.h"

unordered_map <string, sf::Texture> TextureManager::textures;

void TextureManager::LoadTexture(const char* fileName)
{
	string filepath = "images/";
	filepath += fileName;
	filepath += ".png";
	textures[fileName].loadFromFile(filepath);
}
sf::Texture& TextureManager::GetTexture(const char* fileKey)
{
	return textures[fileKey];
}
void TextureManager::Clear()
{
	textures.clear();
}