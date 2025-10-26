import httpx
from bs4 import BeautifulSoup

url =  'https://weather.com/weather/tenday/l/62cf93be794cf17aa6e084abc78cfbe4e30b5215acee12f0c651b32df17f1360'
headers = {
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/141.0.0.0 Safari/537.36"
}

def extract_weather_data(day):
    try:
        tempHigh = day.find('span', class_ = "DetailsSummary--highTempValue--VHKaO").text
        tempLow = day.find('span', class_ = "DetailsSummary--lowTempValue--ogrzb").text
        precipitation = day.find('span', attrs ={'data-testid': 'PercentageValue'}).text
        condition = day.find('span', class_ = "Ellipsis--ellipsis--zynqj").text
        date = day.find('h2', attrs ={'data-testid': 'daypartName'}).text
        print(f'Date: {date} | Temperature: {tempHigh}/{tempLow} | Condition: {condition} | Precipitation: {precipitation}')
    except Exception as e:
        print(e)

def main():
    response = httpx.get(url, headers= headers)
    response_html = response.text

    soup = BeautifulSoup(response_html, 'html.parser')
    weathers = soup.find_all('details',class_ = 'DaypartDetails--DayPartDetail--n5F8Y Disclosure--themeList--vEbAF Disclosure--disableBorder--FMQTf')
    for day in weathers:
        extract_weather_data(day)

    
if __name__ == '__main__':
    main()