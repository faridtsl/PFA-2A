$(document).ready(function() {
    $("#mytable #checkall").click(function () {
        if ($("#mytable #checkall").is(':checked')) {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", true);
            });

        } else {
            $("#mytable input[type=checkbox]").each(function () {
                $(this).prop("checked", false);
            });
        }
    });
    
    $("[data-toggle=tooltip]").tooltip();


    $('.editButton').on('click', function() {
        // Get the record's ID via attribute
        var id = $(this).attr('data-id');
        var tr = $(this).closest("tr");
        $("#id").val(tr.find(".idArt").text());
        $("#titre").val(tr.find(".titreArt").text());
        $("#tendSel").val(tr.find(".tendArt").text());
        $("#catSel").val(tr.find(".catArt").text());
        $("#contenu").val(tr.find(".contenuArt").text());
    });

    $('.deleteButton').on('click', function() {
        // Get the record's ID via attribute
        var id = $(this).attr('data-id');
        var tr = $(this).closest("tr");
        var msg = "Suppresion de '";
        var m2 = msg.concat(tr.find(".titreArt").text());
        var m = m2.concat("'");
        $("#headingDelete").append(m);
        $("#idSupp").val(tr.find(".idArt").text());
    });

    $( '#table' ).searchable({
        striped: true,
        oddRow: { 'background-color': '#f5f5f5' },
        evenRow: { 'background-color': '#fff' },
        searchType: 'fuzzy'
    });
    
    $( '#searchable-container' ).searchable({
        searchField: '#container-search',
        selector: '.row',
        childSelector: '.col-xs-4',
        show: function( elem ) {
            elem.slideDown(100);
        },
        hide: function( elem ) {
            elem.slideUp( 100 );
        }
    })

    $('.clickable').click(function() {
        var msg = $(this).find(".msgText").text();
        var person = $(this).find(".person").text();
        var rep = "Répondre à "
        $("#textMsg").text(msg);
        $("#msgTo").text(rep.concat(person));
    });

});