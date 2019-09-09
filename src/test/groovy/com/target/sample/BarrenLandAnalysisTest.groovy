package com.target.sample

import spock.lang.Specification
import spock.lang.Unroll

class BarrenLandAnalysisTest extends Specification {

    @Unroll
    def "CalculateFertileLandAreas"() {

        given:
        BarrenLandAnalysis barrenLandAnalysis = new BarrenLandAnalysis()

        when:
        String actualResult = barrenLandAnalysis.calculateFertileLandAreas(barrenLand)

        then:
        actualResult == expectedResult

        where:
        expectedResult                                                      || barrenLand
        '116800 116800'                                                     || '{"0 292 399 307"}'
        '22816 192608'                                                      || '{"48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547"}'
        '164686'                                                            || '{"17 111 222 311", "191 499 311 544", "150 72 175 599", "360 52 475 547"}'
        '41026 72300 85202'                                                 || '{"0 111 399 117", "191 499 311 544", "150 72 175 599", "360 52 475 547"}'
        'ERROR : Input value is malformed'                                  || '"0 17 399 307"}'
        'ERROR : Could not parse the input string into integer coordinates' || '{"0 x 399 307"}'

    }
}