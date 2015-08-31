FIWARE Policy Manager - User and Programmers Guide
__________________________________________________


Introduction
============

Welcome the User and Programmer Guide for the Social Semantic Enricher Generic
Enabler. The online documents are being continuously updated and
improved, and so will be the most appropriate place to get the most up
to date information on using this interface.

Background and Detail
---------------------

This User and Programmers Guide relates to the Social Semantic Enricher GE which
is part of the `Data Chapter <Data_Architecture>`__.
Please find more information about this Generic Enabler in the following
`Open Specification <FIWARE.OpenSpecification.Data.SocialSemanticEnricher>`__.

User Guide
==========

The Social Semantic Enricher GE is a backend component, A user interface was provided, but 
since the development of the enabler were stopped before the end of the project it was not possible 
to complete it. Therefore there is no need to provide a user guide. The enabler provides anyway a set
of two main APIs. The "Front End" part of APIs which is detailed here just includes the "extraction" API, 
which for a given URL or TEXT file, returns a clean text to be passed to the Core.

Programmer Guide
================

The Extraction API takes as Input a URL or text file and return clean text, suitable for the SSE CORE classifying API.
Below, parameters and sample request/response are explained

HTTP METHOD: GET

+ Input Parameters
    * url : url to parse
    * file: filedata to parse

+ Output
   * plain text

+ Request (text/plain)

        url=http://www.repubblica.it/cronaca/2015/02/26/news/io_infermiere_vi_racconto_leutanasia_silenziosa_nei_nostri_ospedali-108205220/

+ Response 200 (text/plain)
    
        Repubblica TV
        “Io, infermiere vi racconto l’eutanasia silenziosa nei nostri ospedali”
        È caposala al Careggi di Firenze, cattolico praticante, e ogni anno - dice - nel suo reparto si spengono le macchine per 30-40 malati terminali "La legge lo vieta, ma ce lo chiedono i familiari. Così tra loro e i medici si stringe un patto di buon senso: perché in Italia deve restare un segreto?"
        di MATTEO PUCCIARELLI
        Veronesi: "Molti medici fanno già l'eutanasia
        FIRENZE - Come possiamo definirla? "Eutanasia silenziosa". Per noi è un fatto di tutti i giorni. Lo affrontiamo con grande difficoltà, ma sicuri di fare sempre la cosa più giusta", dice Michele (lo chiameremo così). Una laurea, la specializzazione, il master, la carriera infermieristica, oggi è caposala all'ospedale Careggi di Firenze. Ha voglia di raccontare quello di cui, chissà se per pudore o se per una congiura del silenzio, nessuno parla mai. E di farlo evitando la politica, "ma con il buonsenso di chi sta in prima linea".
        Premessa: Michele non è ateo, anzi, è un cattolico praticante, va a messa due volte alla settimana. Sorride di questa apparente contraddizione, "ma qui Dio non c'entra nulla. Sono un professionista, ho studiato. Se teniamo in vita artificialmente un paziente, siamo noi che ci stiamo sostituendo a Dio...".
        Ogni anno, in un grande reparto come quello dove lavora Michele, medici, infermieri e operatori sanitari hanno a che fare con almeno 30-40 casi di persone sospese in una terra di mezzo dove il confine tra cosa è eutanasia e cosa no è sottilissimo. "Dal punto di vista normativo siamo obbligati a nutrire e idratare anche un vegetale. In queste condizioni un paziente può andare avanti per mesi, o anni", spiega.
        Un po' come avvenne con Eluana Englaro: "Ho perso il conto di quanti malati ho visto così. E da fuori, quando si sta bene, non ci si rende conto di quanto sia facile ritrovarsi in quelle condizioni. Il caso Eluana ci diede una lezione: nessun riflettore, silenzio sulla materia con l'esterno. Poi però mi chiedo se è giusto omettere la verità".
        Appunto, la verità: parenti e dottori sanno capirsi, a volte basta uno sguardo di intesa, di comprensione, di compassione. "Formalmente il medico non può dire "va bene, stacco la macchina" a chi ci chiede un intervento di questo tipo. Ma fa intendere che c'è la possibilità di non accanirsi. Bisogna saper comunicare un concetto ma senza esprimerlo fino in fondo. Tocca fare gli equilibristi con le parole". Ci sono farmaci che tengono su pressione arteriosa e funzionalità respiratorie: "Smettiamo di darli, per esempio. Non facciamo più le cosiddette procedure invasive. Se non c'è alcuna possibilità di ripresa, che senso ha?".
        Uno degli ultimi casi è avvenuto pochi giorni fa: un uomo di 54 anni con problemi di cuore. Un violento edema, le attività cerebrali azzerate. "Abbiamo aspettato due giorni. Ci siamo confrontati coi familiari, la compagna e la madre; i valori non ci lasciavano dubbi. "Non ci sono spiragli. Insistiamo?". In pochi rispondono di sì, morire a volte è una liberazione". Insistendo, invece, quanto sarebbe restato ancora in vita? "Questione di giorni, al massimo due settimane". Spesso le famiglie sono preparate all'eventualità della morte di un congiunto: "Dipende sempre dal male che hanno di fronte. Se c'è un'operazione complicata davanti, per dire, capisci che si sono confrontati anche con il caro, magari un'ora prima di entrare in sala. Sempre sottovoce: noi ce ne accorgiamo che stanno parlando dei "se"".
        Quel confine in realtà è pericoloso per chi ci lavora a cavallo: "Avessimo lo scudo del testamento biologico, sarebbe tutto più semplice. Capita che un parente ci faccia capire qualcosa e poi cambi idea. Ed è normale, perché subentrano sentimenti e paure, sensi di colpa, la speranza dell'impossibile o del miracolo. Oppure non tutta la famiglia è d'accordo, i genitori ad esempio tendono a non rassegnarsi, generi o nuore invece sono più pragmatici. Ma in tutto questo, tu medico da chi sei tutelato? Ci prendiamo dei rischi enormi ". Viene da chiedersi chi glielo faccia fare, ma Michele anticipa la risposta: "Sembrerò crudo, ma un posto letto in un reparto come il mio potrebbe servire a chi ancora, invece, ce la può fare".
        Fin qui però nessuno ha parlato di iniezioni letali, la Svizzera o le invasioni barbariche sono lontane. "Tra colleghi siamo tutti d'accordo, non c'è fede che tenga. Nei turni di notte parliamo: "Se capitasse a me e vedete che non c'è niente da fare, datemi una botta di morfina". Però non so se avrei il coraggio di farlo io a un amico senza uno scudo giuridico", continua Michele, e abbassa lo sguardo per la prima volta. La questione è ancor più aperta in reparti come oncologia: lì la linea di demarcazione è molto più chiara, non ci sono ambiguità: "So solo che sarebbe bello dare la possibilità alle persone di scegliere quando andarsene. Scegliere di morire in maniera degna, in condizioni dignitose, lasciando un bel ricordo di sé agli altri".
        Non sarebbe complicato fare un primo passo: "C'è già adesso la possibilità di avere un tesserino che certifica la volontà di donare gli organi  -  ragiona Michele  -  perché non prevederne uno per il fine vita?". Domande senza
        una risposta, o forse sì, poco importa: "Prima il medico o un prete erano considerati i padroni della vita o della morte. Oggi non ci sono più tabù: il malato sa che ha dei diritti, compreso quello di gestire per sé anche l'ultimo passaggio".
  
