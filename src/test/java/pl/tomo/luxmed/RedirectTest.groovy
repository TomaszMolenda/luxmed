package pl.tomo.luxmed

import spock.lang.Specification

class RedirectTest extends Specification {

    def "shouldCreateRedirectUrl" () {

        given:

        when:
        def redirect = Redirect.builder()
                .path("/test")
                .param("as", "sa")
                .param("er", "ty")
                .build()

        then:
        redirect == "redirect:/test?as=sa&er=ty"
    }
}
