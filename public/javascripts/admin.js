$(document).ready(function() {

    $(".table-striped").on("click", ".btn-danger", function(e) {
        e.preventDefault();
        var id = $(this).data("id");
        var parent = $(this).closest("tr");
        swal({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.value) {
                $.ajax({
                    url: "/admin/coupons/delete",
                    type: 'post',
                    data: {
                        id: id
                    },
                    complete: function(xhr, statusText) {
                        if (xhr.status == 200) {
                            parent.remove();
                            swal('Deleted!', 'The coupon was removed.', 'success')
                        } else swal('Not Deleted!', 'The coupon was Not removed.', 'error')

                    }
                });


            }
        })

    })

    $("#formEdit").on("submit", function(e) {
        e.preventDefault();
        var formObj = $(this);    
        var formURL = formObj.attr("action");
        $.post(formURL, formObj.serialize())
            .done(function(msg) {
                swal({
                    title: ':)',
                    text: "Coupon Edited!",
                    type: 'success'
                }).then((result) => {
                    window.history.back();
                });
            })
            .fail(function(xhr, status, error) {
                swal({
                    title: ':(',
                    text: "Something went wrong!",
                    type: 'error'
                }).then((result) => {
                    window.history.back();
                });
            });
    });

    $("#formAdd").on("submit", function(e) {
        e.preventDefault();
        var formObj = $(this);   
        var formURL = formObj.attr("action");
        $.post(formURL, formObj.serialize())
            .done(function(msg) {
                swal({
                    title: ':)',
                    text: "Coupon Created!",
                    type: 'success'
                }).then((result) => {
                    window.history.back();
                });
            })
            .fail(function(xhr, status, error) {
                swal({
                    title: ':(',
                    text: "Something went wrong!",
                    type: 'error'
                }).then((result) => {
                    window.history.back();
                });
            });
    });

});
