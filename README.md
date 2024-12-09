Run:
http://127.0.0.1:8080/index

Supports:
1. VZ.ru: https://vz.ru/news/2024/12/5/1301716.html
2. Lenta.ru: https://lenta.ru/news/2024/12/05/v-moskovskom-vuze-otvetili-na-obvineniya-v-ugroze-uvolit-prepodavatelya-iz-za-tsveta-volos/
3. IZ.ru: https://iz.ru/1802157/evgeniia-pertceva/vino-i-liubov-kakim-budet-vinnyi-gorod-belyi-mys-v-gelendzhike
4. Sample of non-supported site: "https://www.vedomosti.ru/society/news/2024/12/05/1079628-sputnitse-durova-vernuli-tehniku?from=newsline"


Use this code fetch a page's content to test regexp:

NewsGateway gateway = new NewsGateway();
String newsBody = gateway.getNewsFromURL( "https://iz.ru/1802157/evgeniia-pertceva/vino-i-liubov-kakim-budet-vinnyi-gorod-belyi-mys-v-gelendzhike");
String regex = "<title>(.*?) \\|.*?</title>"; // Regular expression to extract domain

Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
Matcher matcher = pattern.matcher(newsBody);

if (matcher.find()) {
    String domain = matcher.group(1); // Capture the domain
    System.out.println("|||" + domain + "|||");  // Output: vz.ru
} else {
    System.out.println("No match");
}
