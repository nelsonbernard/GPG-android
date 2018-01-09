from bs4 import BeautifulSoup
import urllib.request
import csv

class Game:
	name = ""
	platform = ""
	loose = 0.00
	cib = 0.00
	new = 0.00


scrape_page_main = 'http://www.gamevaluenow.com/platform'
page = urllib.request.urlopen(scrape_page_main)
soup = BeautifulSoup(page, 'html.parser')

platform_link_array = []
game_array = []

for div in soup.find_all('div', attrs={'class':'platform-link'}):
	for a in div.find_all(href=True):
		platform_link_array.append(a['href'])

for link in platform_link_array:
	page = urllib.request.urlopen('http://www.gamevaluenow.com' + link)
	soup = BeautifulSoup(page, 'html.parser')
	table = soup.find('table', {'id':'so_gamelist_table'})

	table_body = soup.find('tbody')
	rows = table_body.find_all('tr')
	for tr in rows:
		cols = tr.find_all('td')

		game = Game()

		if len(cols) == 6:
			number1, name1, loose1, cib1, new1, graded1 = [c.text.strip() for c in cols]
		if len(cols) == 5:
			number1, name1, loose1, cib1, new1 = [c.text.strip() for c in cols]
		if len(cols) == 4:
			number1, name1, loose1, cib1 = [c.text.strip() for c in cols]

		print(name1, loose1, cib1, new1)

		with open('results.csv', "wb") as csv_file:
			# writer = csv.writer(csv_file, delimiter='\t')
			csv_file.writeline(c.text.strip() for c in cols)
		# game = Game()
		# game.name = name1
		# game.platform = link
		# game.loose = loose1
		# game.cib = cib1
		# game.new = new1

		# game_array.append(game)
		# game = Game()
		# game.name = cells[1].find(text=True)
		# game.platform = link
		# game.loose = cells[2].find(text=True)
		# game.cib = cells[3].find(text=True)
		# game.new = cells[4].find(text=True)
		# print(game.name)

