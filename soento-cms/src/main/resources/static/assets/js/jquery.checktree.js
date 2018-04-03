(function (jQuery) {
    jQuery.checkTree = function (id, data, selected) {
        $('#' + id).html(jQuery.checkTreeLi(id, data, selected));
        $('#' + id).checkTree();
    };
    jQuery.checkTreeLi = function (id, data, selected) {
        var html = '';
        for (var i = 0; i < data.length; i++) {
            var d = data[i];
            var checked = '';
            if (selected && jQuery.inArray(d.name, selected) != -1) {
                checked = ' checked="checked"';
            }
            html += '<li>';
            html += '<input type="checkbox" name="' + id + 'Chk" value="' + d.name + '"' + checked + '>';
            html += '<label>' + d.text + '</label>';
            if (d.children && d.children.length > 0) {
                html += '<ul>';
                html += jQuery.checkTreeLi(id, d.children, selected);
                html += '</ul>';
            }
            html += '</li>';
        }
        return html;
    };
    jQuery.fn.checkTree = function (settings) {
        settings = jQuery.extend({
            /* Callbacks
                The callbacks should be functions that take one argument. The checkbox tree
                will return the jQuery wrapped LI element of the item that was checked/expanded.
            */
            onExpand: null,
            onCollapse: null,
            onCheck: null,
            onUnCheck: null,
            onHalfCheck: null,
            onLabelHoverOver: null,
            onLabelHoverOut: null,

            /* Valid choices: 'expand', 'check' */
            labelAction: "expand",

            // Debug (currently does nothing)
            debug: false
        }, settings);

        var $tree = this;

        $tree.find("li")
        // Hide all of the sub-trees
            .find("ul")
            .hide()
            .end()

            // Hide all checkbox inputs
            .find(":checkbox")
            .click(function () {
                var $all = jQuery(this).siblings("ul").find(":checkbox");
                var $checked = $all.filter(":checked");

                if (jQuery(this).is(':checked')) {
                    $all.each(function () {
                        if (!jQuery(this).is(':checked')) {
                            this.click();
                        }
                    });
                } else {
                    $all.each(function () {
                        if (jQuery(this).is(':checked')) {
                            this.click();
                        }
                    });
                }
            })
            .end()


            .find("label")
            // Clicking the labels should expand the children
            .click(function () {
                var action = settings.labelAction;
                switch (settings.labelAction) {
                    case 'expand':
                        jQuery(this).siblings(".arrow").click();
                        break;
                }
            })

            // Add a hover class to the labels when hovering
            .hover(
                function () {
                    jQuery(this).addClass("hover");
                    if (settings.onLabelHoverOver) settings.onLabelHoverOver(jQuery(this).parent());
                },
                function () {
                    jQuery(this).removeClass("hover");
                    if (settings.onLabelHoverOut) settings.onLabelHoverOut(jQuery(this).parent());
                }
            )
            .end()

            .each(function () {
                // Create the image for the arrow (to expand and collapse the hidden trees)
                var $arrow = jQuery('<div class="arrow"></div>');

                // If it has children:
                if (jQuery(this).is(":has(ul)")) {
                    $arrow.addClass("collapsed"); // Should start collapsed

                    // When you click the image, toggle the child list
                    $arrow.click(function () {
                        jQuery(this).siblings("ul").toggle();

                        if (jQuery(this).hasClass("collapsed")) {
                            //toggled = settings.expandedarrow;
                            jQuery(this)
                                .addClass("expanded")
                                .removeClass("collapsed")
                            ;
                            if (settings.onExpand) settings.onExpand(jQuery(this).parent());
                        }
                        else {
                            //toggled = settings.collapsedarrow;
                            jQuery(this)
                                .addClass("collapsed")
                                .removeClass("expanded")
                            ;
                            if (settings.onCollapse) settings.onCollapse(jQuery(this).parent());
                        }
                    });
                }
                // Prepend the arrow and checkbox images to the front of the LI
                jQuery(this)
                    .prepend($arrow)
                ;
            })
        ;
        return $tree;
    };
})(jQuery);