package pl.tomo.luxmed.connection;

import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ConnectionExecutor implements ConnectionService {

    private final RequestDataBuilder requestDataBuilder;
    private final RequestFactory requestFactory;
    private final CookieFetcher cookieFetcher;
    private final CookiePersistor cookiePersistor;

    @Autowired
    ConnectionExecutor(RequestDataBuilder requestDataBuilder, RequestFactory requestFactory, CookieFetcher cookieFetcher, CookiePersistor cookiePersistor) {
        this.requestDataBuilder = requestDataBuilder;
        this.requestFactory = requestFactory;
        this.cookieFetcher = cookieFetcher;
        this.cookiePersistor = cookiePersistor;
    }

    @Override
    @SneakyThrows
    public HtmlResponse postForHtml(ConnectionRequest request) {

        request.addCookie(cookiePersistor.getCookies());

        HttpContent httpContent = requestDataBuilder.createContent(request);
        HttpRequest httpRequest = requestFactory.buildRequest(request, httpContent);

        HttpResponse httpResponse = httpRequest.execute();

        List<Cookie> cookies = cookieFetcher.fetch(httpResponse);
        cookiePersistor.save(cookies);

        Document document = Jsoup.parse(httpResponse.getContent(), httpResponse.getContentCharset().toString(),
                httpResponse.getRequest().getUrl().getHost());

        return new HtmlResponse(httpResponse, document);
    }

    @Override
    @SneakyThrows
    public HtmlResponse getForHtml(ConnectionRequest request) {

        request.addCookie(cookiePersistor.getCookies());

        HttpRequest httpRequest = requestFactory.buildRequest(request);

        HttpResponse httpResponse = httpRequest.execute();
        List<Cookie> cookies = cookieFetcher.fetch(httpResponse);
        cookiePersistor.save(cookies);

        if (request.getUrl().contains("PatientPortal/Home/DisplayPopup?PopupAction=AcceptUserDataPopup&PopupController=User&PopupArea=Users")) {

            // TODO: 12/1/19 zrobic klikniecie forma kontakt
            // leci get https://portalpacjenta.luxmed.pl/PatientPortal/Home/DisplayPopup?PopupAction=AcceptUserDataPopup&PopupController=User&PopupArea=Users
            // leci get https://portalpacjenta.luxmed.pl/PatientPortal/Users/User/AcceptUserDataPopup
            // zwraca mi forma
            // musze wyslac go, kazdy post ma __RequestVerificationToken
            // leci post https://portalpacjenta.luxmed.pl/PatientPortal/Users/User/AcceptUserDataPopupNext
            // __RequestVerificationToken: bd9tNTur1s4KuMRr9oo1CRz9j27xz48_IlJM6F-J3yBA2FuzDgP5dSo4V5n_6En6vAY8wXdtFJUdOWv7WX_mrM2FOBs52Hjj43e7yzTbfCZR5EiTCsYqt8TEfL5xQhYJw5ERQNe509cIGZ4CNZKjGAZ9cmFkJD1v3ncSCC1ft501
            //  Email: tomasz.molenda@gmail.com
            //  PhoneNumber: 667478907
            // zwraca 302 i idzie pod location
        }


        if (httpResponse.getStatusCode() == 302) {
            String location = httpResponse.getHeaders().getLocation();
            String url = "https://portalpacjenta.luxmed.pl" + location;


            ConnectionRequest connectionRequest = ConnectionRequest.builder()
                    .cookie(cookies)
                    .url(url)
                    .httpMethod(HttpMethod.GET)
                    .build();

            return getForHtml(connectionRequest);
        }

        Document document = Jsoup.parse(httpResponse.getContent(), httpResponse.getContentCharset().toString(),
                httpResponse.getRequest().getUrl().getHost());

        return new HtmlResponse(httpResponse, document);
    }
}
