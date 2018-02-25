(function($){
    $.fn.extend({
        autofill: function(data, options) {
            var settings = {
                    findbyname: true,
                    restrict: true
                },
                self = this;

            if ( options ) {
                $.extend( settings, options );
            }

            return this.each(function() {
                $.each( data, function(k, v) {
                    k="."+k;
                    var selector, elt;

                    if ( settings.findbyname ) { // by name

                        selector = '[name$="'+k+'"]';
                        elt = ( settings.restrict ) ? self.find( selector ) : $( selector );

                        if ( elt.length == 1 ) {
                            elt.val( ( elt.attr("type") == "checkbox" ) ? [v] : v );
                        } else if ( elt.length > 1 ) {
                            // radio
                            elt.val([v]);
                        } else {
                            selector = '[name$="'+k+'[]"]';
                            elt = ( settings.restrict ) ? self.find( selector ) : $( selector );
                            elt.each(function(){
                                $(this).val(v);
                            });
                        }

                    } else { // by id

                        selector = '#'+k;
                        elt = ( settings.restrict ) ? self.find( selector ) : $( selector );

                        if ( elt.length == 1 ) {
                            elt.val( ( elt.attr("type") == "checkbox" ) ? [v] : v );
                        } else {
                            var radiofound = false;

                            // radio
                            elt = ( settings.restrict ) ? self.find( 'input:radio[name="'+k+'"]' ) : $( 'input:radio[name="'+k+'"]' );
                            elt.each(function(){
                                radiofound = true;
                                if ( this.value == v ) { this.checked = true; }
                            });
                            // multi checkbox
                            if ( !radiofound ) {
                                elt = ( settings.restrict ) ? self.find( 'input:checkbox[name="'+k+'[]"]' ) : $( 'input:checkbox[name="'+k+'[]"]' );
                                elt.each(function(){
                                    $(this).val(v);
                                });
                            }
                        }
                    }
                });
            });
        }
    });
})(jQuery);