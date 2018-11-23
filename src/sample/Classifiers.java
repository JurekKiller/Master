package sample;

import java.util.Map;

import static java.util.Map.entry;

public class Classifiers {
    public static final Map<String, String> map = Map.ofEntries(
            entry("LBPcascade_europe", "Klasyfikatory/lpdcascade_eu.xml"),
            entry("LBPcascade_poland", "Klasyfikatory/ldpcascade_pl.xml"),
            entry("HAARcascade_europe", "Klasyfikatory/haarrcascade_eu.xml"),
            entry("HAARcascade_russia", "Klasyfikatory/haarcascade_rus.xml"),
            entry("HAARcascade_europe_rag", "Klasyfikatory/haar_rag.xml")
    );
}
